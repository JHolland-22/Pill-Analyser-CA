public  abstract class StudentGrade {

    private String studentName;
    private String moduleName;

    private int grade;

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public String getModuleName() {
        return moduleName;
    }

    public void setModuleName(String moduleName) {
        this.moduleName = moduleName;
    }

    public int getGrade() {
        return grade;
    }

    public void setGrade(int grade) {
        this.grade = grade;
    }

    public String classifyGrade() {
        String fail = "Fail";
        String pass = "Pass";
        String merit = "Merit";
        String distinction = " Distinction";
        if (getGrade() >= 39) {
            return fail;
        }
        if (getGrade() >= 49) {
            return pass;
        }
        if (getGrade() >= 69) {
            return merit;
        }
        if (getGrade() >= 100) {
            return distinction;
        } else
            return fail;
    }

    public String letterGrade() {
        String a = "A";
        String b = "B";
        String c = "C";
        String d = " D";
        if (getGrade() >= 39) {
            return d;
        }
        if (getGrade() >= 49) {
            return c;
        }
        if (getGrade() >= 69) {
            return b;
        }
        if (getGrade() >= 100) {
            return a;
        } else
            return d;
    }

    public int percentageGrade() {
        int i = getGrade();

        if (getGrade() >= 39) {
            return getGrade();
        }
        if ((getGrade()) >= 49) {
            return getGrade();
        }

            if (getGrade() >= 69) {
                return getGrade();
            }


                if (getGrade() >= 100) {
                    return getGrade();
                } else return 0;

            }

    }



        




