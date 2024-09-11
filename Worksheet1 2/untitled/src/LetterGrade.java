public class LetterGrade extends StudentGrade {
    private char grade;

    @Override
    public String classifyGrade() {
        return(grade<='B')?"Distinction": (grade =='C')?"Merit": (grade =='D')?"Pass": "Fail";
    }

    @Override
    public int getGrade() {
        return grade-64;
    }
    public void setGrade(char grade){
        grade=Character.toUpperCase(grade);
        if(grade>='A'&& grade <='E')
            this.grade=grade;
        else throw new IllegalArgumentException(grade +"is not a valid grade");
    }
}

