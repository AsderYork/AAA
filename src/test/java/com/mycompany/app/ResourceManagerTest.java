package com.mycompany.app;

import com.triplea.dao.ResourceDataAccess;
import com.triplea.domain.ResourceData;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import static org.mockito.Mockito.*;
import com.triplea.ResourceManager;

/**
 * Created by Asder on 07.12.2016.
 */
public class ResourceManagerTest {

    @Test
    public void TestcheckRoleNull()
    {
        ResourceDataAccess RDA = mock(ResourceDataAccess.class);
        ResourceManager RM = new ResourceManager(RDA);
        boolean Result = RM.isResourceAccessible(1,null,null);
        assertEquals(Result, false);
    }

    @Test
    public void TestcheckRoleWrong()
    {
        ResourceDataAccess RDA = mock(ResourceDataAccess.class);
        ResourceManager RM = new ResourceManager(RDA);
        boolean Result = RM.isResourceAccessible(1,"W","W");
        assertEquals(Result, false);
    }
    @Test
    public void TestcheckRoleREAD()
    {
        ResourceDataAccess RDA = mock(ResourceDataAccess.class);
        ResourceManager RM = new ResourceManager(RDA);
        ResourceData RD = new ResourceData(1,"W",1);
        when(RDA.getResourceDataByIDAndPath(1,"W") ).thenReturn(RD);

        boolean Result = RM.isResourceAccessible(1,"W","READ");
        assertEquals(Result, true);
    }

    @Test
    public void TestcheckRoleWRITE()
    {
        ResourceDataAccess RDA = mock(ResourceDataAccess.class);
        ResourceManager RM = new ResourceManager(RDA);
        ResourceData RD = new ResourceData(1,"W",2);
        when(RDA.getResourceDataByIDAndPath(1,"W") ).thenReturn(RD);

        boolean Result = RM.isResourceAccessible(1,"W","WRITE");
        assertEquals(Result, true);
    }

    @Test
    public void TestcheckRoleEXEC()
    {
        ResourceDataAccess RDA = mock(ResourceDataAccess.class);
        ResourceManager RM = new ResourceManager(RDA);
        ResourceData RD = new ResourceData(1,"W",4);
        when(RDA.getResourceDataByIDAndPath(1,"W") ).thenReturn(RD);

        boolean Result = RM.isResourceAccessible(1,"W","EXECUTE");
        assertEquals(Result, true);
    }

    @Test
    public void TestcheckRoleMM()
    {
        ResourceDataAccess RDA = mock(ResourceDataAccess.class);
        ResourceManager RM = new ResourceManager(RDA);
        ResourceData RD = new ResourceData(1,"W",4);
        when(RDA.getResourceDataByIDAndPath(1,"W") ).thenReturn(null);

        boolean Result = RM.isResourceAccessible(1,"W","EXECUTE");
        assertEquals(Result, false);
    }
    @Test
    public void TestResData()
    {
        ResourceData RD = new ResourceData();
    }

}
