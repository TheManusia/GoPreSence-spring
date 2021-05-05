package xyz.themanusia.gopresence.json.laporan;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import xyz.themanusia.gopresence.json.user.User;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "laporan")
public class Laporan {

    @Id
    @Column(name = "id_laporan")
    private int id;

    @JoinColumn(name = "id_user")
    @ManyToOne
    private User user;

    @JsonFormat(pattern = "yyyy-MM-dd")
    @Column(name = "tanggal")
    private Date date;

    @Column(name = "type")
    private String type;
}
