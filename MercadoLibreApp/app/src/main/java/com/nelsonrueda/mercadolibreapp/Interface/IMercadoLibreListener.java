package com.nelsonrueda.mercadolibreapp.Interface;

/**
 *Interfaz para que la Manejador de la peticioan API Noitifique los Erorres
 */
public interface IMercadoLibreListener {

    void ReportError(String TAG, String Message);
}
