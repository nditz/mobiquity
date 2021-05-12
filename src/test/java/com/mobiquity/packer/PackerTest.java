/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mobiquity.packer;

import com.mobiquity.enums.FileType;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author wandi
 */
public class PackerTest {
    
    public PackerTest() {
    }
    
    @BeforeAll
    public static void setUpClass() {
    }
    
    @AfterAll
    public static void tearDownClass() {
    }
    
    @BeforeEach
    public void setUp() {
    }
    
    @AfterEach
    public void tearDown() {
    }

    /**
     * Test of pack method, of class Packer.
     */
    @Test
    public void testPack_String() throws Exception {
        System.out.println("pack");
        String filePath = "";
        String expResult = "";
        String result = Packer.pack(filePath);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of pack method, of class Packer.
     */
    @Test
    public void testPack_String_FileType() throws Exception {
        System.out.println("pack");
        String filePath = "";
        FileType fileType = null;
        String expResult = "";
        String result = Packer.pack(filePath, fileType);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
