/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import data.Area;
import java.util.List;

/**
 *
 * @author tranq
 */
public interface AreaDAO {

    public List<Area> getAllAreas();

    public boolean createNewArea(Area a);

    public Area findAreaByName(String nameArea);

    public boolean updateArea(Area a);

    public List<Area> checkExistArea(String oldNameArea, String newNameArea);

    public boolean deleteArea(int areaId, String nameArea);

    public Area findAreaById(int areaId);
}
