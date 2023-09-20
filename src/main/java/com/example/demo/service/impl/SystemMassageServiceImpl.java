package com.example.demo.service.impl;

import com.example.demo.entity.msg.ErrorMsg;
import com.example.demo.enums.EnumInterfase;
import com.example.demo.enums.SystemEnum;
import com.example.demo.handler.exceptions.BusinessException;
import com.example.demo.service.SystemMassageService;
import com.example.demo.utils.MassageUtil;
import org.springframework.stereotype.Service;

import java.lang.reflect.Method;
import java.util.List;


@Service
public class SystemMassageServiceImpl implements SystemMassageService {

    /**
     * 获取所有错误码枚举类
     *
     * @return
     */
    @Override
    public List<ErrorMsg> getAllErrorEnums() {
        try {
            return getErrorEnums(EnumInterfase.class.getPackage().getName());
        } catch (Exception e) {
            e.printStackTrace();
            throw new BusinessException(SystemEnum.SYSTEM_002);
        }
    }


    /**
     * 获取枚举错误信息
     * @param packagePath
     * @return
     * @throws Exception
     */
    private List<ErrorMsg> getErrorEnums(String packagePath) throws Exception {
        List<ErrorMsg> allImplEnums = MassageUtil.getAllImplEnums(packagePath, EnumInterfase.class, (constant, method) -> {
            ErrorMsg errorMsg = new ErrorMsg();
            try {
                Method key = method.getDeclaredMethod(MassageUtil.KEY_METHOD);
                Method enDescriptionMethod = method.getDeclaredMethod(MassageUtil.EN_DESCRIPTION_METHOD);
                Method zhDescriptionMethod = method.getDeclaredMethod(MassageUtil.ZH_DESCRIPTION_METHOD);
                errorMsg.setKey((String) key.invoke(constant));
                errorMsg.setEnDescription((String) enDescriptionMethod.invoke(constant));
                errorMsg.setZhDescription((String) zhDescriptionMethod.invoke(constant));
            } catch (Exception e) {
                e.printStackTrace();
                throw new BusinessException(SystemEnum.SYSTEM_002);
            }
            return errorMsg;
        });
        return allImplEnums;
    }
}
