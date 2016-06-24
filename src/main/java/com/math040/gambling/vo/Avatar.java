package com.math040.gambling.vo;
 
import javax.persistence.Entity; 
import javax.persistence.Table;

@Entity
@Table(name = "TR_AVATAR") 
public class Avatar extends BaseDto{    
	 
	private byte[] image;

	public byte[] getImage() {
		return image;
	}

	public void setImage(byte[] image) {
		this.image = image;
	}
	 
}
