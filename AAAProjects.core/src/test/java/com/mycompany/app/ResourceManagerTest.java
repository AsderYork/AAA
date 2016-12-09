package com.mycompany.app;

import com.triplea.dao.ResourceDataAccess;
import com.triplea.domain.ResourceData;
import org.junit.Test;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;
import com.triplea.ResourceManager;

/**
 * Created by Asder on 07.12.2016.
 */
public class ResourceManagerTest {

    @Test
    public void testcheckRoleNull()
    {
        ResourceDataAccess rda = mock(ResourceDataAccess.class);
        ResourceManager rm = new ResourceManager(rda);
        boolean result = rm.isResourceAccessible(1,null,null);
        assertFalse(result);
    }

    @Test
    public void testcheckRoleWrong()
    {
        ResourceDataAccess rda = mock(ResourceDataAccess.class);
        ResourceManager rm = new ResourceManager(rda);
        boolean result = rm.isResourceAccessible(1,"W","W");
        assertFalse(result);
    }
    @Test
    public void testcheckRoleREAD()
    {
        ResourceDataAccess rda = mock(ResourceDataAccess.class);
        ResourceManager rm = new ResourceManager(rda);
        ResourceData rd = new ResourceData(1,"W",1);
        when(rda.getResourceDataByIDAndPath(1,"W") ).thenReturn(rd);

        boolean result = rm.isResourceAccessible(1,"W","READ");
        assertTrue(result);
    }

    @Test
    public void testcheckRoleWRITE()
    {
        ResourceDataAccess rda = mock(ResourceDataAccess.class);
        ResourceManager rm = new ResourceManager(rda);
        ResourceData rd = new ResourceData(1,"W",2);
        when(rda.getResourceDataByIDAndPath(1,"W") ).thenReturn(rd);

        boolean result = rm.isResourceAccessible(1,"W","WRITE");
        assertTrue(result);
    }

    @Test
    public void testcheckRoleEXEC()
    {
        ResourceDataAccess rda = mock(ResourceDataAccess.class);
        ResourceManager rm = new ResourceManager(rda);
        ResourceData rd = new ResourceData(1,"W",4);
        when(rda.getResourceDataByIDAndPath(1,"W") ).thenReturn(rd);

        boolean result = rm.isResourceAccessible(1,"W","EXECUTE");
        assertTrue(result);
    }

    @Test
    public void testcheckRoleMM()
    {
        ResourceDataAccess rda = mock(ResourceDataAccess.class);
        ResourceManager rm = new ResourceManager(rda);

        when(rda.getResourceDataByIDAndPath(1,"W") ).thenReturn(null);

        boolean result = rm.isResourceAccessible(1,"W","EXECUTE");
        assertFalse(result);
    }
}
