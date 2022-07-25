package com.nelsonrueda.mercadolibreapp.Interface;

/**
 *Interfaz para que las los adaptores notifique eventos
 */
public interface IAdapterListener {
    /**
     * Evento de onCliclItemSelect para notificar la vista seleccionada desde el adaptador
     * @param data
     */
    void onClickItemSelect(Object data);
}
