package xyz.themanusia.gopresence.network.holiday.response;

import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class JsonResponse {
    @SerializedName("items")
    private List<ItemsItem> items;
}