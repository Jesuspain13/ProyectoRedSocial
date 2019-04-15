/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package redSocial.modelos;

import com.google.gson.Gson;
import java.util.List;

/**
 *
 * @author Jesus
 */
public class Sugerencias {
    
    private List<Usuario> friendsSuggested;
    private List<Grupos> groupsSuggested;
    
    public Sugerencias() {};
    
    public Sugerencias(List<Usuario> friendsSuggested, List<Grupos> groupsSuggested) {
        this.friendsSuggested = friendsSuggested;
        this.groupsSuggested = groupsSuggested;
    }

    public List<Usuario> getFriendsSuggested() {
        return friendsSuggested;
    }

    public void setFriendsSuggested(List<Usuario> friendsSuggested) {
        this.friendsSuggested = friendsSuggested;
    }

    public List<Grupos> getGroupsSuggested() {
        return groupsSuggested;
    }

    public void setGroupsSuggested(List<Grupos> groupsSuggested) {
        this.groupsSuggested = groupsSuggested;
    }
    
    public String suggestionToJson(List<Usuario> users, List<Grupos> groups) {
        Gson gson = new Gson();
        this.friendsSuggested = Usuario.friendsToJson(users);
        this.groupsSuggested = Grupos.prepareToJson(groups);
        String suggestion = gson.toJson(this);
        return suggestion;
    }
    
    
}
