package com.nelsonrueda.mercadolibreapp.Interface;


import com.nelsonrueda.mercadolibreapp.Entities.Models.Site;

import java.util.ArrayList;

/**
 * Intefaz para comunicar las informacion obtenidad de las peticiones API de Sites
 */
public interface ISitesListener {

    void GetSites(ArrayList<Site> sites);
}
