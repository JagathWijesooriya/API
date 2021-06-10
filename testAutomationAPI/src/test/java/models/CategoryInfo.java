package models;

import java.util.List;

public class CategoryInfo {
	private int CategoryId;
	private String Name;
	private boolean CanReList;
	private boolean CanListClassifieds;
	private List<Promotion> promotions;

	public List<Promotion> getPromotions() {
		return promotions;
	}

	public void setPromotions(List<Promotion> promotions) {
		this.promotions = promotions;
	}

	public int getCategoryId() {
		return CategoryId;
	}

	public void setCategoryId(int categoryId) {
		CategoryId = categoryId;
	}

	public String getName() {
		return Name;
	}

	public void setName(String name) {
		Name = name;
	}

	public boolean isCanReList() {
		return CanReList;
	}

	public void setCanReList(boolean canReList) {
		CanReList = canReList;
	}

	public boolean isCanListClassifieds() {
		return CanListClassifieds;
	}

	public void setCanListClassifieds(boolean canListClassifieds) {
		CanListClassifieds = canListClassifieds;
	}

}
