/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package redSocial.svc.implementations;

import java.util.List;
import javax.ejb.Stateless;
import redSocial.modelos.Amistades;
import redSocial.modelos.ComentariosGrupos;
import redSocial.modelos.Grupos;
import redSocial.modelos.Post;
import redSocial.modelos.Usuario;
import redSocial.svc.interfaces.Upgradeable;

/**
 *
 * @author Jesus
 */
@Stateless
public class UpgradeableImpl implements Upgradeable {
    
    private static UpgradeableImpl upgradeableObj;
    
    public static UpgradeableImpl getUpgradeableImplFactory(){
        if (UpgradeableImpl.upgradeableObj == null) {
            UpgradeableImpl.upgradeableObj = new UpgradeableImpl();
        }
        return upgradeableObj;
    }

    @Override
    public void updateUser(Usuario user, Object thingToAddOrDelete, int toDo) {
        if (thingToAddOrDelete instanceof Amistades) {  
            UpgradeableImpl.updateRelationship(user, thingToAddOrDelete, toDo);
        } else if (thingToAddOrDelete instanceof Post) {
            UpgradeableImpl.updatePost(user, thingToAddOrDelete, toDo);
        } else if (thingToAddOrDelete instanceof Grupos) {
            UpgradeableImpl.updateGroup(user, thingToAddOrDelete, toDo);
            
        } else if (thingToAddOrDelete instanceof ComentariosGrupos) {
            UpgradeableImpl.updateComment(user, thingToAddOrDelete, toDo);
        }
    }
    
    @Override
    public void updateGroupComponents(Grupos grupoaActualizar, Object thingToAddOrDelete, int toDo) {
        if (thingToAddOrDelete instanceof Usuario) {
            Usuario newUserCasted = (Usuario) thingToAddOrDelete;        
            List<Usuario> olderUsers = grupoaActualizar.getUsuarioList();
            if (toDo == 1) {
                olderUsers.add(newUserCasted);
            } else if(toDo == 2) {
                olderUsers.remove(newUserCasted);
            }
            grupoaActualizar.setUsuarioList(olderUsers);
        } else if (thingToAddOrDelete instanceof ComentariosGrupos){
            ComentariosGrupos newCommentCasted = (ComentariosGrupos) thingToAddOrDelete;        
            List<ComentariosGrupos> olderComments = grupoaActualizar.getComentariosList();
            if (toDo == 1) {
                olderComments.add(newCommentCasted);
            } else if(toDo == 2) {
                olderComments.remove(newCommentCasted);
            }
            grupoaActualizar.setComentariosList(olderComments);
        }
    }

    /**
     * actualiza la lista de relaiones del usuario
     * @param user usuario a acualizar
     * @param newFriend usuario a añadir o borrar
     * @param toDo añadir o borrar
     */
    private static void updateRelationship(Usuario user, Object newFriend, int toDo) {
        Amistades newFriendCasted = (Amistades) newFriend;
        List<Amistades> olderFriends = user.getAmistadesList();
        // si toDo es 1 es que se va a añadir
        if (toDo == 1) {
            olderFriends.add(newFriendCasted);
        // si es 2 es que se va borrar
        } else if(toDo == 2) {
            olderFriends.remove(newFriendCasted);
        }
        user.setAmistadesList(olderFriends);
    }
    
    /**
     * actualizar los Post del usuario
     * @param user usuario a actualizar
     * @param newPost post a añadir o borrar
     * @param toDo añadir o borrar
     */
    private static void updatePost(Usuario user, Object newPost, int toDo) {
        Post newPostCasted = (Post) newPost;
        List<Post> olderPosts = user.getPostList();
        if (toDo == 1) {
            olderPosts.add(newPostCasted);
        } else if(toDo == 2) {
            olderPosts.remove(newPostCasted);
        }
        user.setPostList(olderPosts);
    }
    
    /**
     * actualizar los Post del usuario
     * @param user usuario a actualizar
     * @param newPost post a añadir o borrar
     * @param toDo añadir o borrar
     */
    private static void updateComment(Usuario user, Object newPost, int toDo) {
        ComentariosGrupos newCommentCasted = (ComentariosGrupos) newPost;
        Grupos grupo = newCommentCasted.getIdGrupal();
        
        List<ComentariosGrupos> olderComments = grupo.getComentariosList();
        if (toDo == 1) {
            olderComments.add(newCommentCasted);
        } else if(toDo == 2) {
            olderComments.remove(newCommentCasted);
        }
        grupo.setComentariosList(olderComments);
    }
    
    /**
     * actualizar grupos de usuario
     * @param user usuario a actualizar
     * @param newGroup grupo a añadir o borrar
     * @param toDo añadir o borrar
     */
    private static void updateGroup(Usuario user, Object newGroup, int toDo) {
        Grupos newGroupCasted = (Grupos) newGroup;
        List<Grupos> olderGroups = user.getGruposList();
        if (toDo == 1) {
            olderGroups.add(newGroupCasted);
        } else if(toDo == 2) {
            olderGroups.remove(newGroupCasted);
        }
        user.setGruposList(olderGroups);
    }

    
    
}
