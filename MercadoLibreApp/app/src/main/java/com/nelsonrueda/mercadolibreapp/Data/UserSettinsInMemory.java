package com.nelsonrueda.mercadolibreapp.Data;

import android.content.res.Resources;

import com.nelsonrueda.mercadolibreapp.Entities.Models.Category;
import com.nelsonrueda.mercadolibreapp.Entities.Models.ChildrenCategory;
import com.nelsonrueda.mercadolibreapp.Entities.Models.Site;
import com.nelsonrueda.mercadolibreapp.R;

/**
 * Una clase que permitira persirtir en memoria mientras que la acc esta corriendo
 * la informacion o instacia de objectos con datos, como seria el ejemplo de Site de
 * pais seleccionado para poder complementar la informacion para la peticion a las demas
 * API
 */
public class UserSettinsInMemory {
    private static Site sCurrentSite = null;
    private static Object sCurrentCategory = null;

    public static Site GetCurrentSite(){
        return sCurrentSite;
    }

    public static void SetCurrentSite(Site site){
        sCurrentSite = site;
    }

    public static void SetCurrentCategory(Object category){
        sCurrentCategory = category;
    }

    public static String GetCurrentCategoryID() throws Exception {
        if(sCurrentCategory != null){
            if(sCurrentCategory instanceof Category){
                return ((Category)sCurrentCategory).getId();
            }else if(sCurrentCategory instanceof ChildrenCategory){
                return ((ChildrenCategory)sCurrentCategory).getId();
            }else{
                throw new Exception(Resources.getSystem().getString(R.string.current_category_not_is_instanced_valid));
            }
        }else{
            throw new Exception(Resources.getSystem().getString(R.string.current_category_not_selected));
        }
    }
}
