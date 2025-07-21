package com.arthur.schedulingApi.models.ratingImpl;

import com.arthur.schedulingApi.models.Rating;
import com.arthur.schedulingApi.models.Services;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class ServiceRating extends Rating {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "serviceRatee_id")
    private Services serviceRatee;

    public void setServiceRatee(Services serviceRatee) {
        this.serviceRatee = serviceRatee;
    }
}
