package com.ontariotechu.sofe3980U;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;

import org.junit.runner.RunWith;

import org.springframework.test.context.junit4.*;

@RunWith(SpringRunner.class)
@WebMvcTest(BinaryController.class)
public class BinaryControllerTest {

    @Autowired
    private MockMvc mvc;
   
    @Test
    public void getDefault() throws Exception {
        this.mvc.perform(get("/"))//.andDo(print())
            .andExpect(status().isOk())
            .andExpect(view().name("calculator"))
			.andExpect(model().attribute("operand1", ""))
			.andExpect(model().attribute("operand1Focused", false));
    }
	
    @Test
    public void getParameter() throws Exception {
        this.mvc.perform(get("/").param("operand1","111"))
            .andExpect(status().isOk())
            .andExpect(view().name("calculator"))
			.andExpect(model().attribute("operand1", "111"))
			.andExpect(model().attribute("operand1Focused", true));
    }
	@Test
	    public void postParameter() throws Exception {
        this.mvc.perform(post("/").param("operand1","111").param("operator","+").param("operand2","111"))//.andDo(print())
            .andExpect(status().isOk())
            .andExpect(view().name("result"))
			.andExpect(model().attribute("result", "1110"))
			.andExpect(model().attribute("operand1", "111"));
    }
    // New tests
    @Test
    public void postZero() throws Exception {
        this.mvc.perform(post("/").param("operand1","000").param("operator","+").param("operand2","0000"))//.andDo(print())
                .andExpect(status().isOk())
                .andExpect(view().name("result"))
                .andExpect(model().attribute("result", "0"))
                .andExpect(model().attribute("operand1", "000"));
    }
    @Test
    public void getOperator() throws Exception {
        this.mvc.perform(get("/").param("operator","+"))
                .andExpect(status().isOk())
                .andExpect(view().name("calculator"));
    }
    @Test
    public void postInvalid() throws Exception {
        this.mvc.perform(post("/").param("operand1","1").param("operator"," ").param("operand2","0"))
                .andExpect(status().isOk())
                .andExpect(view().name("error"));
    }
    // New multiply
    @Test
    public void postMultiply() throws Exception {
        this.mvc.perform(post("/").param("operand1","111").param("operator","*").param("operand2","111"))//.andDo(print())
                .andExpect(status().isOk())
                .andExpect(view().name("result"))
                .andExpect(model().attribute("result", "110001"))
                .andExpect(model().attribute("operand1", "111"));
    }
    @Test
    public void postMultiplyZero() throws Exception {
        this.mvc.perform(post("/").param("operand1","0").param("operator","*").param("operand2","111"))//.andDo(print())
                .andExpect(status().isOk())
                .andExpect(view().name("result"))
                .andExpect(model().attribute("result", "0"))
                .andExpect(model().attribute("operand1", "0"));
    }
    // New and
    @Test
    public void postAnd() throws Exception {
        this.mvc.perform(post("/").param("operand1","1010").param("operator","&").param("operand2","0101"))//.andDo(print())
                .andExpect(status().isOk())
                .andExpect(view().name("result"))
                .andExpect(model().attribute("result", "0"))
                .andExpect(model().attribute("operand1", "1010"));
    }
    // New or
    @Test
    public void postOr() throws Exception {
        this.mvc.perform(post("/").param("operand1","1010").param("operator","|").param("operand2","0101"))//.andDo(print())
                .andExpect(status().isOk())
                .andExpect(view().name("result"))
                .andExpect(model().attribute("result", "1111"))
                .andExpect(model().attribute("operand1", "1010"));
    }
}