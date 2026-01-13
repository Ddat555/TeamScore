package org.teamscore.individualTask.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.teamscore.individualTask.models.entity.TypePayment;
import org.teamscore.individualTask.repositories.TypePaymentRepository;

import java.util.List;

@Service
public class TypePaymentService {

    @Autowired
    private TypePaymentRepository typePaymentRepository;

    public TypePayment createTypePayment(TypePayment typePayment){
        return typePaymentRepository.save(typePayment);
    }

    public TypePayment updateTypePayment(TypePayment typePayment){
        var oldTypePayment = typePaymentRepository.findById(typePayment.getId()).orElseThrow();
        oldTypePayment.setName(typePayment.getName());
        return typePaymentRepository.save(oldTypePayment);
    }

    public void deleteTypePayment(Long id){
        typePaymentRepository.deleteById(id);
    }

    public void deleteTypePayment(TypePayment typePayment){
        typePaymentRepository.delete(typePayment);
    }

    public List<TypePayment> getAllTypePayment(Pageable pageable){
        return typePaymentRepository.findAll(pageable);
    }

    public TypePayment getTypePaymentByName(String name){
        return typePaymentRepository.findByName(name).orElseThrow();
    }


}
