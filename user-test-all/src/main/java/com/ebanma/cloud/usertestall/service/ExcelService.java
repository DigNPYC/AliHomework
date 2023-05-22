package com.ebanma.cloud.usertestall.service;

import com.ebanma.cloud.usertestall.domain.dto.UserQueryDTO;

public interface ExcelService {
    void export(String filename, UserQueryDTO userQueryDTO);

    void asyncExport(String filename, UserQueryDTO userQueryDTO);
}
