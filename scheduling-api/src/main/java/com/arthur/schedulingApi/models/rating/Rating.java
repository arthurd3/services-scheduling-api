package com.arthur.schedulingApi.models.rating;

import com.arthur.schedulingApi.models.user.User;
import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Rating {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull
    private String description;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "appraiser_id")
    private User appraiser;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ratee_id")
    private User ratee;

    @Min(0)
    @Max(5)
    private Integer score;

}
