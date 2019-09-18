package fr.simplon.testApi.moneer.services;

import fr.simplon.testApi.moneer.dtos.UserAccountCreateDto;

public interface UserAccountService {

    void create(UserAccountCreateDto dto);

    void deleteById(Long id);
    
}
