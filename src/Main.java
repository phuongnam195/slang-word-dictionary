import java.util.Scanner;
import java.util.ArrayList;

import model.SearchHistory;
import model.SlangWord;
import repository.HistoryRepository;
import service.SlangService;

public class Main {
    public static void main(String[] args) {
        SlangService.start();
        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println();
            System.out.println("             MENU");
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
            int choice;
            try {
                choice = Integer.parseInt(sc.nextLine());
            } catch (Exception e) {
                continue;
            }

            switch (choice) {
                case 1: {
                    // Tìm kiếm theo slang word
                    System.out.print("> Nhập slang word cần tìm: ");
                    String word = sc.nextLine();
                    SlangWord result = SlangService.findByWord(word);
                    if (result == null) {
                        System.out.println("--> Không tìm thấy slang word!");
                    } else {
                        ArrayList<String> definitions = result.getDefinitions();
                        System.out.print("--> Kết quả tìm kiếm: ");
                        for (int i = 0; i < definitions.size(); i++) {
                            System.out.print(definitions.get(i));
                            if (i < definitions.size() - 1) {
                                System.out.print(", ");
                            }
                        }
                        System.out.println();
                    }
                    break;
                }

                case 2: {
                    // Tìm kiếm theo definition
                    System.out.print("> Nhập definition cần tìm: ");
                    String definition = sc.nextLine();
                    ArrayList<SlangWord> result = SlangService.findByDefinition(definition);
                    if (result == null) {
                        System.out.println("--> Không tìm thấy slang word!");
                    } else {
                        System.out.print("--> Kết quả tìm kiếm: ");
                        for (int i = 0; i < result.size(); i++) {
                            System.out.print(result.get(i).getWord());
                            if (i < result.size() - 1) {
                                System.out.print(", ");
                            }
                        }
                        System.out.println();
                    }
                    break;
                }
                case 3: {
                    HistoryRepository hisRepo = HistoryRepository.getInstance();
                    ArrayList<SearchHistory<SlangWord>> history = hisRepo.loadAllHistory();
                    for (SearchHistory<SlangWord> log : history) {
                        System.out.println(log.getTime() + " | \"" + log.getKeyword() + "\" --> " + log.getResult());
                    }
                    break;
                }
                case 4: {
                    // Thêm slang word mới
                    System.out.println("  Thêm slang word mới");
                    System.out.print("  + Nhập slang word: ");
                    String word = sc.nextLine().trim();
                    SlangWord newSW = new SlangWord(word);
                    System.out.print("  + Nhập định nghĩa: ");
                    String definition = sc.nextLine().trim();
                    newSW.addDefinition(definition);
                    while (true) {
                        System.out.print("  + Nhập thêm định nghĩa (bỏ trống để dừng nhập): ");
                        definition = sc.nextLine().trim();
                        if (definition.isBlank()) {
                            break;
                        }
                        newSW.addDefinition(definition);
                    }
                    SlangService.addSlangWord(newSW);
                    break;
                }

                case 5: {
                    // Sửa slang word
                    System.out.print("> Nhập slang word cần sửa: ");
                    String word = sc.nextLine().trim();
                    SlangWord newSW = new SlangWord(word);
                    System.out.print("  + Nhập định nghĩa mới: ");
                    String definition = sc.nextLine().trim();
                    newSW.addDefinition(definition);
                    while (true) {
                        System.out.print("  + Nhập thêm định nghĩa (bỏ trống để dừng nhập): ");
                        definition = sc.nextLine().trim();
                        if (definition.isBlank()) {
                            break;
                        }
                        newSW.addDefinition(definition);
                    }
                    if (SlangService.updateSlangWord(newSW)) {
                        System.out.println("--> Sửa thành công.");
                    } else {
                        System.out.println("--> Sửa thất bại!");
                    }
                    break;
                }
                case 6: {
                    // Xóa slang word
                    System.out.print("> Nhập slang word cần xóa: ");
                    String word = sc.nextLine().trim();
                    if (SlangService.removeSlangWord(word)) {
                        System.out.println("--> Xóa thành công.");
                    } else {
                        System.out.println("--> Xóa thất bại!");
                    }
                    break;
                }
                case 7:
                    SlangService.resetSlangWord();
                    System.out.println("--> Khôi phục thành công!");
                    break;
                case 8:
                    System.out.println("--> On this day slang word: " + SlangService.getRandomWord());
                    break;
                case 9:
                    break;
                case 10:
                    break;
                case 0:
                    SlangService.stop();
                    sc.close();
                    return;
                default:
            }
        }
    }
}
