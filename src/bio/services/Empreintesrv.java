/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bio.services;
import bio.models.Empreinte;
import bio.dao.IDAO;
/**
 *
 * @author Diamondra
 */
public abstract class Empreintesrv {
    public static final String TABLE_NAME = "empreintes";
  
    public static final String COL_ID = "id";

    public static final String COL_DOIGT = "doigt";

    public static final String COL_IMAGE = "image";

    public static final String COL_COLLABORATEUR_ID = "collaborateur_id";

    public static final String INSERT = "INSERT INTO empreintes(doigt, image, collaborateur_id) VALUES(?,AES_ENCRYPT(?,'key'), ?)";

    public static final String DELETE = "DELETE FROM empreintes WHERE id=?";

    public static final String FIND = "SELECT * FROM empreintes WHERE id=?";

    public static final String FIND_BY_PERSONNE_ID_DOIGT = "SELECT * FROM empreintes WHERE collaborateur_id=? AND doigt=?";

    public static final String FIND_IMAGE_BY_PERSONNE_ID_DOIGT = "SELECT AES_DECRYPT(image,'key')as blobImg FROM empreintes WHERE collaborateur_id=? AND doigt=?";

    public static final String FIND_ALL = "SELECT * FROM empreintes";

    public static final String FIND_ALL_BY_PESONNE_ID = "SELECT * FROM empreintes WHERE collaborateur_id=?";

    public static final String UPDATE = "UPDATE empreintes SET doigt=?, image=?, collaborateur_id=?,  WHERE id=?";
}
