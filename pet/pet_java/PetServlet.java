package com.test.sku.pet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;

import java.io.IOException;
import java.nio.file.Paths;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

@WebServlet("/pet")
@MultipartConfig // 파일 업로드 지원
public class PetServlet extends HttpServlet {
    private PetService petService = new PetService();

    // PetAction enum 정의
    private enum PetAction {
        LIST, DETAIL, EDIT, DELETE, ADD, UPDATE, DEFAULT
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String actionParam = request.getParameter("action");
        PetAction action = PetAction.DEFAULT; // 기본값 설정

        try {
            action = PetAction.valueOf(actionParam.toUpperCase()); // 문자열을 enum으로 변환
        } catch (IllegalArgumentException e) {
        	response.sendRedirect(request.getContextPath() + "/error?message=Invalid+action");
        }

        switch (action) {
            case LIST:
                List<PetVO> petList = petService.getAllPets();
                request.setAttribute("petList", petList);
                request.getRequestDispatcher("/pet/petList.jsp").forward(request, response);
                break;
            case DETAIL:
                int no = Integer.parseInt(request.getParameter("no"));
                PetVO pet = petService.getPetByNo(no);
                request.setAttribute("pet", pet);
                request.getRequestDispatcher("/pet/petDetail.jsp").forward(request, response);
                break;
            case EDIT:
                int noToEdit = Integer.parseInt(request.getParameter("no"));
                PetVO petToEdit = petService.getPetByNo(noToEdit);
                if (petToEdit == null) {
                    request.setAttribute("errorMessage", "수정할 펫 정보가 없습니다.");
                } else {
                    request.setAttribute("pet", petToEdit);
                }
                request.getRequestDispatcher("/pet/petEdit.jsp").forward(request, response);
                break;
            case DELETE:
                try {
                    int noToDelete = Integer.parseInt(request.getParameter("no"));
                    petService.deletePet(noToDelete);
                    response.sendRedirect(request.getContextPath() + "/pet?action=list");
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            default:
                response.sendRedirect("pet_index.jsp");
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String actionParam = request.getParameter("action");
        PetAction action = PetAction.DEFAULT;

        try {
            action = PetAction.valueOf(actionParam.toUpperCase());
        } catch (Exception e) {
            e.printStackTrace();
        }

        switch (action) {
        	case ADD:
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

        	        petService.addPet(petToAdd);
        	        response.sendRedirect(request.getContextPath() + "/pet?action=list"); // 목록 페이지로 리다이렉트
        	    } catch (NumberFormatException | ParseException e) {
        	        e.printStackTrace();
        	        return;
        	    }
        	    break;
        	case UPDATE:
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
        	        if (filePart != null && filePart.getSize() > 0) { // 새로운 이미지 파일이 업로드된 경우
        	            String fileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString();
        	            filePart.write("webapp/img/pet/" + fileName); // 이미지 저장 경로 확인
        	            petToUpdate.setPic(fileName);

        	            // 기존 이미지 파일 삭제 (선택 사항)
        	            // String oldPic = petService.getPetByNo(petToUpdate.getNo()).getPic();
        	            // Files.deleteIfExists(Paths.get("webapp/img/pet/" + oldPic));
        	        } else {
        	            // 새로운 이미지 파일이 업로드되지 않은 경우, 기존 이미지 파일 정보 유지
        	            petToUpdate.setPic(petService.getPetByNo(petToUpdate.getNo()).getPic());
        	        }

        	        petService.updatePet(petToUpdate);
        	        response.sendRedirect(request.getContextPath() + "/pet?action=list"); // 목록 페이지로 리다이렉트
        	    } catch (Exception e) {
        	        e.printStackTrace();
        	        return;
        	    }
        	    break;
        }
    }
}