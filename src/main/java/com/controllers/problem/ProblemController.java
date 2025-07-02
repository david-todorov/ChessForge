package com.controllers.problem;

import com.controllers.Controller;
import com.model.problems.Problems;

public interface ProblemController extends Controller {


    void setProblem(Problems problem);


    boolean createMatch();
}
