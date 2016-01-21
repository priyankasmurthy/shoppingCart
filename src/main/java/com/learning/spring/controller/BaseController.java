package com.learning.spring.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;

import com.learning.spring.Service.InventoryService;

public class BaseController {

	@Autowired
	public InventoryService inventoryService;

	public List<String> getColorList() {
		List<String> listOfColors = new ArrayList<String>();
		listOfColors.add("Red");
		listOfColors.add("Green");
		listOfColors.add("White");
		listOfColors.add("Black");
		listOfColors.add("Blue");
		listOfColors.add("Yellow");
		listOfColors.add("Pink");

		return listOfColors;
	}

	public List<String> getAllList() {
		List<String> completeItemList = new ArrayList<String>();
		completeItemList.addAll(getCategoryList());
		completeItemList.addAll(getBrandsList("All"));
		return completeItemList;
	}

	public List<String> getCategoryList() {
		List<String> listOfCategories = new ArrayList<String>();
		listOfCategories.add("Handbags");
		listOfCategories.add("Watches");
		listOfCategories.add("Footwear");

		return listOfCategories;
	}

	public List<String> getBrandsList(String selectedCategory) {

		List<String> listOfHandbagBrands = new ArrayList<String>();
		listOfHandbagBrands.add("Butterfly");
		listOfHandbagBrands.add("Lavie");
		listOfHandbagBrands.add("Caprese");

		List<String> listOfWatchBrands = new ArrayList<String>();
		listOfWatchBrands.add("Titan Raga");
		listOfWatchBrands.add("Fastrack");
		listOfWatchBrands.add("Casio");

		List<String> listOfFootwearBrands = new ArrayList<String>();
		listOfFootwearBrands.add("Soles");
		listOfFootwearBrands.add("Metro");
		listOfFootwearBrands.add("Catwalk");

		List<String> listOfBrands = new ArrayList<String>();
		listOfBrands.addAll(listOfHandbagBrands);
		listOfBrands.addAll(listOfWatchBrands);
		listOfBrands.addAll(listOfFootwearBrands);

		if (StringUtils.hasText(selectedCategory)) {
			if (selectedCategory.equals("Handbags")) {
				return listOfHandbagBrands;
			} else if (selectedCategory.equals("Watches")) {
				return listOfWatchBrands;
			} else if (selectedCategory.equals("Footwear")) {
				return listOfFootwearBrands;
			} else {
				return listOfBrands;
			}
		}

		return listOfBrands;
	}
}
