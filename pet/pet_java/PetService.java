package com.test.sku.pet;

import java.util.List;

public class PetService
{
	private PetDAO petDAO = new PetDAO();

    public List<PetVO> getAllPets() {
        return petDAO.getAllPets();
    }

    public PetVO getPetByNo(int no) {
        return petDAO.getPetByNo(no);
    }

    public void addPet(PetVO pet) throws IllegalArgumentException {
        // 입력값 유효성 검사
        if (pet.getName() == null || pet.getName().isEmpty()) {
            throw new IllegalArgumentException("펫 이름은 필수입니다.");
        }
        if (pet.getOrigin() == null || pet.getOrigin().isEmpty()) {
            throw new IllegalArgumentException("원산지는 필수입니다.");
        }
        if (pet.getWeight() <= 0) {
            throw new IllegalArgumentException("몸무게는 0보다 커야 합니다.");
        }
        if (pet.getBirth() == null) {
            throw new IllegalArgumentException("생년월일은 필수입니다.");
        }
        if (pet.getPrice() <= 0) {
            throw new IllegalArgumentException("가격은 0보다 커야 합니다.");
        }
        if (pet.getPic() == null || pet.getPic().isEmpty()) {
            throw new IllegalArgumentException("사진은 필수입니다.");
        }

        petDAO.insertPet(pet);
    }

    public void updatePet(PetVO pet) throws IllegalArgumentException {
        // 입력값 유효성 검사 (addPet 메소드와 동일한 로직 적용)
        if (pet.getName() == null || pet.getName().isEmpty()) {
            throw new IllegalArgumentException("펫 이름은 필수입니다.");
        }
        if (pet.getOrigin() == null || pet.getOrigin().isEmpty()) {
            throw new IllegalArgumentException("원산지는 필수입니다.");
        }
        if (pet.getWeight() <= 0) {
            throw new IllegalArgumentException("몸무게는 0보다 커야 합니다.");
        }
        if (pet.getBirth() == null) {
            throw new IllegalArgumentException("생년월일은 필수입니다.");
        }
        if (pet.getPrice() <= 0) {
            throw new IllegalArgumentException("가격은 0보다 커야 합니다.");
        }

        // 사진은 필수가 아닐 수 있으므로 검증하지 않음
        petDAO.updatePet(pet);
    }

    public void deletePet(int no) {
        petDAO.deletePet(no);
    }
}