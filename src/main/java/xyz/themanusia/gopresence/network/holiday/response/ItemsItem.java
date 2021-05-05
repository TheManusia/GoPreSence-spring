package xyz.themanusia.gopresence.network.holiday.response;

import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ItemsItem{

	@SerializedName("summary")
	private String summary;

	@SerializedName("start")
	private Start start;

}