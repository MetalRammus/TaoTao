package com.zhiyou100.portal.pojo;

import java.io.Serializable;
import java.util.Arrays;

import com.zhiyou100.model.TbItem;

public class ItemInfo extends TbItem implements Serializable{

	public String[] getImages() {
		String image = getImage();
		if (image != null) {
			String[] images = image.split(",");
			return images;
		}
		return null;
	}

	@Override
	public String toString() {
		return "ItemInfo [getImages()=" + Arrays.toString(getImages()) + ", getId()=" + getId() + ", getTitle()="
				+ getTitle() + ", getSellPoint()=" + getSellPoint() + ", getPrice()=" + getPrice() + ", getNum()="
				+ getNum() + ", getBarcode()=" + getBarcode() + ", getImage()=" + getImage() + ", getCid()=" + getCid()
				+ ", getStatus()=" + getStatus() + ", getCreated()=" + getCreated() + ", getUpdated()=" + getUpdated()
				+ ", getClass()=" + getClass() + ", hashCode()=" + hashCode() + ", toString()=" + super.toString()
				+ "]";
	}
	
	
}
