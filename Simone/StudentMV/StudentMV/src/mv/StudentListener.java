package mv;

import java.util.ArrayList;

public interface StudentListener {

    void onUpdateAge(int age);
    void onUpdateLetter(char letter);
    void onUpdateGrades(ArrayList<Integer> grades);
    void onUpdatefullName(String fullName);

   // String getFullName();

}
