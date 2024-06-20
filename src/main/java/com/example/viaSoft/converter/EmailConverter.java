package com.example.viaSoft.converter;

import com.example.viaSoft.DTO.EmailDTO;

public interface EmailConverter<T> {
    T convert(EmailDTO emailDTO);
}
