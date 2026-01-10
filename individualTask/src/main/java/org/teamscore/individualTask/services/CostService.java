package org.teamscore.individualTask.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.teamscore.individualTask.models.entity.Cost;
import org.teamscore.individualTask.repositories.CostRepository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class CostService {

    @Autowired
    private CostRepository costRepository;

    public Cost createCost(Cost cost){
        return costRepository.save(cost);
    }

    public Cost updateCost(Cost cost){
        var oldCost = costRepository.findById(cost.getId()).orElseThrow();
        oldCost.setCategories(cost.getCategories());
        oldCost.setSum(cost.getSum());
        oldCost.setSellerName(cost.getSellerName());
        return costRepository.save(oldCost);
    }

    public void deleteCost(Long id){
        costRepository.deleteById(id);
    }

    public void deleteCost(Cost cost){
        costRepository.delete(cost);
    }

    public List<Cost> getAllCost(Pageable pageable){
        return costRepository.findAll(pageable);
    }

    public Cost getCostById(Long id){
        return costRepository.findById(id).orElseThrow();
    }

    public List<Cost> getAllCostByPeriod(LocalDateTime dateFrom, LocalDateTime dateTo){
        return costRepository.findAllByPeriod(dateFrom,dateTo);
    }
}
