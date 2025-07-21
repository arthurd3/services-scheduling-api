package com.arthur.schedulingApi.models.ratingImpl;

import com.arthur.schedulingApi.models.Rating;
import com.arthur.schedulingApi.models.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class UserRating extends Rating {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ratee_id")
    private User ratee;

    public void setRatee(User ratee) {
        this.ratee = ratee;
    }
}
