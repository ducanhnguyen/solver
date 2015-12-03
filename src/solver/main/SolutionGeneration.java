package solver.main;

import java.util.ArrayList;

import solver.solutionStandardStrategy.IStandardStrategy;
import solver.solutionStandardStrategy.Z3StandardStrategy;
import solver.solverStrategy.ISolver;
import solver.solverStrategy.z3.Z3Configuration;
import solver.solverStrategy.z3.Z3Solver;

public class SolutionGeneration {

    private String solution = "";
    private int solvingTime = 0;

    public static void main(String[] args) throws Exception {
        /**Step 1: Input*/ 
    	ArrayList<String> testcases = new ArrayList<>();
        testcases.add("int a");
        testcases.add("int b");
        testcases.add("int c");
        
        ArrayList<String> constraints = new ArrayList<>();
        constraints.add("a==c");
        constraints.add("a>b+2");
        constraints.add("!(a<6)&&(b>0)");
        
        /**Step 2: Config Z3*/
        Z3Configuration.Smt_Lib_path_file="D:\\";// duong dan luu cac thong tin tam thoi trong qua trinh solve
        Z3Configuration.Smt_Lib_path_lib = "D:\\z3\\bin\\z3.exe";// duong dan den thu vien z3.exe
        
        /**Step 3: Solve*/
        SolutionGeneration gen = new SolutionGeneration(); 
        gen.setSolverStrategy(new Z3Solver(testcases, constraints));
        gen.setSolutionStandardStrategy(new Z3StandardStrategy(gen.getOutput()));
        System.out.println("Z3: Normalize solution: " + gen.getOutput());
   }

    public SolutionGeneration() throws Exception {
    }

    public void setSolverStrategy(ISolver solver) {
        solution = solver.getSolution();
        solvingTime = solver.getSolvingTime();
    }

    public void setSolutionStandardStrategy(IStandardStrategy standardStrategy) {
        solution = standardStrategy.getNormalizeSolution();
    }

    public String getOutput() {
        return solution;
    }

    public int getSolvingTime() {
        return solvingTime;
    }
}
