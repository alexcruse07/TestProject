import java.util.ArrayList;
import java.util.List;

public class Test3 {

  public static void main(String[] args) {
    System.out.println(getQuerySearchParameter("role_name==er or org_unit==dsf"));
  }

  public static List<String> getQuerySearchParameter(String query) {
    List<String> queryParams = new ArrayList<>();
    query = query.trim();
    String[] parts = query.split("(,(?![^()]*\\))|;|,| or | and )");
    for (String p : parts) {
        queryParams.add(p.trim());
    }
    return queryParams;
}
}
