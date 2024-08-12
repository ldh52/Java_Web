package com.test.sku.pet;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;

import java.io.IOException;
import java.nio.file.Paths;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

public class PetService {
    private HttpServletRequest request;
    private HttpServletResponse response;
    private PetDAO petDAO;

    private void sendJSON(boolean success, String message) throws IOException {
        response.setContentType("application/json;charset=UTF-8");
        // 직접 JSON 문자열 생성
        String json = "{\"success\": " + success + ", \"message\": \"" + message + "\"}";
        response.getWriter().write(json);
    }
    
    public PetService(HttpServletRequest request, HttpServletResponse response) {
        this.request = request;
        this.response = response;
        this.petDAO = new PetDAO();
    }

    public String process() throws ServletException, IOException {
        String action = request.getParameter("action");

        switch (action == null ? "" : action) {
            case "list":
                return list();
            case "detail":
                return detail();
            case "edit":
                return edit();
            case "delete":
                return delete();
            case "add":
                return add();
            case "update":
                return update();
            case "search":
                String category = request.getParameter("category"); 
                String keyword = request.getParameter("keyword");
                return search(category, keyword); 
            default:
                return "/pet/pet_index.jsp"; // 기본적으로 pet_index.jsp로 이동
        }
    }

    // 각 기능별 메소드 구현
    private String list() throws ServletException, IOException {
        List<PetVO> petList = petDAO.getAllPets(); 
        request.setAttribute("petList", petList);  
        return "/pet/petList.jsp";                 
    }
    
    private String search(String category, String keyword) throws ServletException, IOException {
    	List<PetVO> petList;
        if (keyword != null && !keyword.isEmpty()) {
            petList = petDAO.searchPets(category, keyword); 
        } else {
            petList = petDAO.getAllPets();
        }
        request.setAttribute("petList", petList);
        return "/pet/petList.jsp";
    }

    private String detail() throws ServletException, IOException {
        int no = Integer.parseInt(request.getParameter("no"));
        PetVO pet = petDAO.getPetByNo(no);
        request.setAttribute("pet", pet);
        return "/pet/petDetail.jsp";
    }

    private String edit() throws ServletException, IOException {
        int noToEdit = Integer.parseInt(request.getParameter("no"));
        PetVO petToEdit = petDAO.getPetByNo(noToEdit);
        request.setAttribute("pet", petToEdit);
        return "/pet/petEdit.jsp";
    }

    private String delete() throws IOException {
        int noToDelete = Integer.parseInt(request.getParameter("no"));
        petDAO.deletePet(noToDelete);
        response.sendRedirect(request.getContextPath() + "/pet?action=list"); // 목록 페이지로 리다이렉트
        return null;
    }

    private String add() throws ServletException, IOException {
        try {
            PetVO petToAdd = new PetVO();
            petToAdd.setName(request.getParameter("name"));
            petToAdd.setOrigin(request.getParameter("origin"));
            petToAdd.setWeight(Double.parseDouble(request.getParameter("weight")));

            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            petToAdd.setBirth(dateFormat.parse(request.getParameter("birth")));

            petToAdd.setPrice(Integer.parseInt(request.getParameter("price")));

            Part filePart = request.getPart("pic");
            String fileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString();
            filePart.write("webapp/img/pet/" + fileName); // 이미지 저장 경로 확인
            petToAdd.setPic(fileName);

            petDAO.insertPet(petToAdd);
            response.sendRedirect(request.getContextPath() + "/pet?action=list"); 
            return null;
        } catch (NumberFormatException | ParseException e) {
            request.setAttribute("errorMessage", "잘못된 입력 형식입니다.");
            return "/pet/petInput.jsp";
        } catch (Exception e) {
            request.setAttribute("errorMessage", "펫 추가 실패");
            return "/pet/petInput.jsp";
        }
    }

    private String update() throws ServletException, IOException {
        try {
            PetVO petToUpdate = new PetVO();
            petToUpdate.setNo(Integer.parseInt(request.getParameter("no")));
            petToUpdate.setName(request.getParameter("name"));
            petToUpdate.setOrigin(request.getParameter("origin"));
            petToUpdate.setWeight(Double.parseDouble(request.getParameter("weight")));

            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            petToUpdate.setBirth(dateFormat.parse(request.getParameter("birth")));

            petToUpdate.setPrice(Integer.parseInt(request.getParameter("price")));

            Part filePart = request.getPart("pic");
            if (filePart != null && filePart.getSize() > 0) { 
                String fileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString();
                filePart.write("webapp/img/pet/" + fileName);
                petToUpdate.setPic(fileName);
            } else {
                petToUpdate.setPic(petDAO.getPetByNo(petToUpdate.getNo()).getPic());
            }
            petDAO.updatePet(petToUpdate);
            response.sendRedirect(request.getContextPath() + "/pet?action=list");
            return null;
        } catch (NumberFormatException | ParseException e) {
            request.setAttribute("errorMessage", "잘못된 입력 형식입니다.");
            return "/pet/petEdit.jsp";
        } catch (Exception e) {
            request.setAttribute("errorMessage", "펫 수정 실패");
            return "/pet/petEdit.jsp";
        }
    }
}