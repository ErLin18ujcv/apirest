package com.example.demo.service;
import com.example.demo.entity.Empleados;
import com.example.demo.exceptions.BusinessException;
import com.example.demo.exceptions.NotFoundException;
import java.util.List;

public interface IEmpleadosService {
    Empleados        saveEmpleados(Empleados empleados) throws BusinessException;
    List <Empleados> saveEmpleados (List<Empleados> empleados) throws BusinessException;
    List <Empleados> getEmpleados() throws BusinessException;
    Empleados        getEmpleadosById(long id)throws BusinessException, NotFoundException;
    Empleados        getEmpleadoByNombre(String nombre) throws BusinessException,NotFoundException;
    void             deleteEmpleados(long id) throws BusinessException,NotFoundException;
    Empleados        updateEmpleados(Empleados empleados) throws BusinessException,NotFoundException;
}
