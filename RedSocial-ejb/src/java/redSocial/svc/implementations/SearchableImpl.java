/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package redSocial.svc.implementations;

import java.util.Iterator;
import java.util.List;
import javax.ejb.Stateless;
import redSocial.modelos.Amistades;
import redSocial.modelos.Grupos;
import redSocial.modelos.Post;
import redSocial.svc.interfaces.Searchable;

/**
 *
 * @author Jesus
 */
@Stateless
public class SearchableImpl implements Searchable {
    
    private static SearchableImpl searchableObj;
    
    public static SearchableImpl getSearchableImplFactory(){
        if (SearchableImpl.searchableObj == null) {
            SearchableImpl.searchableObj = new SearchableImpl();
        }
        return searchableObj;
    }

    @Override
    public Object search(List listOfSomething, int id) {
        Object resultado = null;
        if (listOfSomething.get(0) instanceof Amistades) {  
            resultado = SearchableImpl.searchRelationship(listOfSomething, id);
        } else if (listOfSomething.get(0) instanceof Post) {
            resultado = SearchableImpl.searchPost(listOfSomething, id);
        } else if (listOfSomething.get(0) instanceof Grupos) {
            resultado = SearchableImpl.searchGroup(listOfSomething, id);
        }
        return resultado;
    }
    
    private static Object searchRelationship(List listOfSomething, int id) {
        Iterator r = listOfSomething.iterator();
        boolean encontrado = false;
        Amistades valueIteration;
        Amistades result = null;
        while (r.hasNext() && !encontrado) {
            valueIteration = (Amistades) r.next();
            if (valueIteration.getAmistadesid() == id) {
                result = valueIteration;
                encontrado = true;
            }
        }
        return result;
}
    
    private static Object searchPost(List listOfSomething, int id) {
        Iterator r = listOfSomething.iterator();
        boolean encontrado = false;
        Post valueIteration;
        Post result = null;
        while (r.hasNext() && !encontrado) {
            valueIteration = (Post) r.next();
            if (valueIteration.getIdPost()== id) {
                result = valueIteration;
                encontrado = true;
            }
        }
        return result;
}
    
    private static Object searchGroup(List listOfSomething, int id) {
        Iterator r = listOfSomething.iterator();
        boolean encontrado = false;
        Grupos valueIteration;
        Grupos result = null;
        while (r.hasNext() && !encontrado) {
            valueIteration = (Grupos) r.next();
            if (valueIteration.getIdgrupos() == id){
                result = valueIteration;
                encontrado = true;
            }
        }
        return result;
}
}
