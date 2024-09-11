public  class PercentageGrade extends StudentGrad {

    private int grade;


    @Override
    public String classifyGrade() {

       //if (grade >=70) return "Distinction";
      // else if (grade >=50) return "Merit";
      //  else if (grade >=40) return "Pass";
      //else return "Fail";
        // they both mean the same thing the bottom is just more efficient
        //? means if not
        //! means not != not equal to so on...



        return (grade >=70) ? "Distinction": (grade >=50) ? "Merit" : (grade >=40) ? "Pass" : "Fail";
    }

    @Override
    public int getGrade() {
        return grade;
    }

    public void setGrade(int grade) throws IllegalAccessException {
        if (grade >= 0 && grade <= 100) this.grade = grade;
        else throw new IllegalAccessException(grade + "is not a valid grade %");


    }

}

