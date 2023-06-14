package JavaTest;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class SearchSubject {
    private List<String> key; // 학과명 저장
    private SubjectsDao dao;
    private Map<String, List<Subject>> dept;

    public SearchSubject(SubjectsDao dao){
        this.dao = dao;
        this.dept = new HashMap<>();
    }

    public void constructMap() throws SQLException {
        this.key = dao.getKey();
        String strKey = null;
        List<Subject> value = null;
        for (int i = 0; i < key.size(); i++) {
            strKey = key.get(i);
            value = dao.getSubjectList(strKey);
            this.dept.put(strKey, value);
        } //for
    }

    public void printValue(String key) {
        List<Subject> list = null;
        if (dept.containsKey(key)) {
            list = dept.get(key);
            System.out.println("["+key+"]");
            for (Subject s : list)
                System.out.println(s + "\n");
        }

    }

}
