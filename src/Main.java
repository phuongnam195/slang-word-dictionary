import java.util.Scanner;
import java.util.HashSet;

import model.SlangWord;
import service.SlangService;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("   [1] Tìm kiếm theo slang word");
        System.out.println("   [2] Tìm kiếm theo definition");
        System.out.println("   [3] Hiển thị lịch sử tìm kiếm");
        System.out.println("   [4] Thêm slang word mới");
        System.out.println("   [5] Sửa slang word");
        System.out.println("   [6] Xóa slang word");
        System.out.println("   [7] Khôi phục danh sách gốc");
        System.out.println("   [8] On this day slang word");
        System.out.println("   [9] Đố vui: tìm definition cho slang word");
        System.out.println("  [10] Đố vui: tìm slang word cho definition");
        System.out.println("   [0] Thoát chương trình");
        System.out.println();
        System.out.print("> Nhập lựa chọn: ");
        int choice = Integer.parseInt(sc.nextLine());

        switch (choice) {
            case 1:
                System.out.print("> Nhập slang word cần tìm: ");
                String word = sc.nextLine();
                SlangWord result = SlangService.findByWord(word);
                HashSet<String> definitions = result.getDefinitions();
                System.out.print("  Kết quả tìm kiếm: ");
                for (String def : definitions) {
                    System.out.print(def + ", ");
                }
                System.out.println();
                break;
            case 2:
                break;
            case 3:
                break;
            case 4:
                break;
            case 5:
                break;
            case 6:
                break;
            case 7:
                break;
            case 8:
                break;
            case 9:
                break;
            case 10:
                break;
            case 0:
                break;
                default: 
        }
        sc.close();
    }
}
