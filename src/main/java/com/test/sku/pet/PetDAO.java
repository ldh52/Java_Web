package com.test.sku.pet;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import java.sql.*;

import java.util.ArrayList;
import java.util.List;

public class PetDAO 
{
	private Connection conn;

	public PetDAO() {
        conn = getConn();
    }
	
	private Connection getConn() {
        try {
            Class.forName("oracle.jdbc.OracleDriver");
            conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "SCOTT", "TIGER");
            return conn;
        } catch (Exception e) {
            e.printStackTrace(); 
        }
        return null;
    }

    public List<PetVO> getAllPets() 
    {
        List<PetVO> petList = new ArrayList<>();
        String sql = "SELECT * FROM pet ORDER BY no";
        
        try (PreparedStatement pstmt = conn.prepareStatement(sql);
                ResultSet rs = pstmt.executeQuery()) {
            while (rs.next()) {
                PetVO pet = new PetVO();
                pet.setNo(rs.getInt("no"));
                pet.setName(rs.getString("name"));
                pet.setOrigin(rs.getString("origin"));
                pet.setWeight(rs.getDouble("weight"));
                pet.setBirth(rs.getDate("birth")); 
                pet.setPrice(rs.getInt("price"));
                pet.setPic(rs.getString("pic"));
                petList.add(pet);
            }
            return petList;
        } catch (Exception e) {
            e.printStackTrace(); 
        } finally {
        	close();
        }
        return null;
     }

    public PetVO getPetByNo(int no) {
        PetVO pet = null;
        String sql = "SELECT * FROM pet WHERE no = ?";

        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
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
        } catch (SQLException e) {
            // 로깅 대신 콘솔에 에러 메시지 출력 (개발 환경에서만 사용)
            System.err.println("펫 정보 조회 중 오류 발생 (no=" + no + "): " + e.getMessage());
            e.printStackTrace();
        }
        return pet;
    }

    public void insertPet(PetVO pet) {
        String sql = "INSERT INTO pet (name, origin, weight, birth, price, pic) VALUES (?, ?, ?, ?, ?, ?)";

        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, pet.getName());
            pstmt.setString(2, pet.getOrigin());
            pstmt.setDouble(3, pet.getWeight());
            pstmt.setDate(4, toSqlDate(pet.getBirth()));
            pstmt.setInt(5, pet.getPrice());
            pstmt.setString(6, pet.getPic());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            // 로깅 대신 콘솔에 에러 메시지 출력 (개발 환경에서만 사용)
            System.err.println("펫 정보 추가 중 오류 발생: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public void updatePet(PetVO pet) {
        String sql = "UPDATE pet SET weight = ?, price = ? WHERE no = ?";

        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setDouble(1, pet.getWeight());
            pstmt.setInt(2, pet.getPrice());
            pstmt.setInt(3, pet.getNo()); 

            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deletePet(int no) {
        String sql = "DELETE FROM pet WHERE no = ?";

        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, no);
            int affectedRows = pstmt.executeUpdate();
        } catch (SQLException e) {
            // 로깅 대신 콘솔에 에러 메시지 출력 (개발 환경에서만 사용)
            System.err.println("펫 정보 삭제 중 오류 발생 (no=" + no + "): " + e.getMessage());
            e.printStackTrace();
        }
    }

    private Date toSqlDate(java.util.Date utilDate) {
        return new Date(utilDate.getTime());
    }
    
    public void close() {
        try {
            if (conn != null && !conn.isClosed()) {
                conn.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<PetVO> searchPets(String category, String keyword) {
        List<PetVO> petList = new ArrayList<>();
        String sql;

        if ("no".equals(category)) { // 번호로 검색
            sql = "SELECT * FROM pet WHERE no = ? ORDER BY no"; 
        } else { // 이름으로 검색 (기본값)
            sql = "SELECT * FROM pet WHERE name LIKE ? ORDER BY no"; 
        }

        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            if ("no".equals(category)) {
                try {
                    int no = Integer.parseInt(keyword);
                    pstmt.setInt(1, no);
                } catch (NumberFormatException e) {
                    return petList; // 빈 목록 반환
                }
            } else {
                String likeKeyword = "%" + keyword + "%";
                pstmt.setString(1, likeKeyword);
            }

            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    PetVO pet = new PetVO();
                    pet.setNo(rs.getInt("no"));
                    pet.setName(rs.getString("name"));
                    pet.setOrigin(rs.getString("origin"));
                    pet.setWeight(rs.getDouble("weight"));
                    pet.setBirth(rs.getDate("birth"));
                    pet.setPrice(rs.getInt("price"));
                    pet.setPic(rs.getString("pic"));
                    petList.add(pet);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return petList;
    }
}