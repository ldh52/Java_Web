package com.test.sku.pet;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PetDAO {

    private DataSource dataSource;

    public PetDAO() {
        try {
            Context initContext = new InitialContext();
            Context envContext = (Context) initContext.lookup("java:/comp/env");
            dataSource = (DataSource) envContext.lookup("jdbc/pet_db");
        } catch (Exception e) {
            e.printStackTrace(); // 로깅 대신 콘솔에 출력 (개발 환경에서만 사용)
        }
    }

    public List<PetVO> getAllPets() {
        List<PetVO> petList = new ArrayList<>();
        String sql = "SELECT * FROM pet";

        try (Connection conn = dataSource.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                PetVO pet = new PetVO();
                pet.setNo(rs.getInt("no"));
                pet.setName(rs.getString("name"));
                pet.setOrigin(rs.getString("origin"));
                pet.setWeight(rs.getDouble("weight"));
                pet.setBirth(rs.getDate("birth"));  // 날짜 타입으로 가져오기
                pet.setPrice(rs.getInt("price"));
                pet.setPic(rs.getString("pic"));
                petList.add(pet);
            }
        } catch (Exception e) {
            e.printStackTrace(); // 로그에 에러 출력 (실제 운영 환경에서는 적절한 예외 처리 필요)
        }
        return petList;
    }

    public PetVO getPetByNo(int no) {
        PetVO pet = null;
        String sql = "SELECT * FROM pet WHERE no = ?";

        try (Connection conn = dataSource.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, no);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    pet = new PetVO();
                    pet.setNo(rs.getInt("no"));
                    pet.setName(rs.getString("name"));
                    pet.setOrigin(rs.getString("origin"));
                    pet.setWeight(rs.getDouble("weight"));
                    pet.setBirth(rs.getDate("birth"));
                    pet.setPrice(rs.getInt("price"));
                    pet.setPic(rs.getString("pic"));
                }
            }
        } catch (Exception e) {
            // 로깅 대신 콘솔에 에러 메시지 출력 (개발 환경에서만 사용)
            System.err.println("펫 정보 조회 중 오류 발생 (no=" + no + "): " + e.getMessage());
            e.printStackTrace(); 
        }

        return pet;
    }

    public void insertPet(PetVO pet) {
        String sql = "INSERT INTO pet (name, origin, weight, birth, price, pic) VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection conn = dataSource.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, pet.getName());
            pstmt.setString(2, pet.getOrigin());
            pstmt.setDouble(3, pet.getWeight());
            pstmt.setDate(4, toSqlDate(pet.getBirth()));
            pstmt.setInt(5, pet.getPrice());
            pstmt.setString(6, pet.getPic());
            pstmt.executeUpdate();
        } catch (Exception e) {
            // 로깅 대신 콘솔에 에러 메시지 출력 (개발 환경에서만 사용)
            System.err.println("펫 정보 추가 중 오류 발생: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public void updatePet(PetVO pet) {
        String sql = "UPDATE pet SET name = ?, origin = ?, weight = ?, birth = ?, price = ?, pic = ? WHERE no = ?";

        try (Connection conn = dataSource.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, pet.getName());
            pstmt.setString(2, pet.getOrigin());
            pstmt.setDouble(3, pet.getWeight());
            pstmt.setDate(4, toSqlDate(pet.getBirth()));
            pstmt.setInt(5, pet.getPrice());
            pstmt.setString(6, pet.getPic());
            pstmt.setInt(7, pet.getNo());
            pstmt.executeUpdate();
        } catch (Exception e) {
            // 로깅 대신 콘솔에 에러 메시지 출력 (개발 환경에서만 사용)
            System.err.println("펫 정보 수정 중 오류 발생 (no=" + pet.getNo() + "): " + e.getMessage());
            e.printStackTrace();
        }
    }

    public void deletePet(int no) {
        String sql = "DELETE FROM pet WHERE no = ?";

        try (Connection conn = dataSource.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, no);
            pstmt.executeUpdate();
        } catch (Exception e) {
            // 로깅 대신 콘솔에 에러 메시지 출력 (개발 환경에서만 사용)
            System.err.println("펫 정보 삭제 중 오류 발생 (no=" + no + "): " + e.getMessage());
            e.printStackTrace();
        }
    }

    private Date toSqlDate(java.util.Date utilDate) {
        return new Date(utilDate.getTime());
    }
}