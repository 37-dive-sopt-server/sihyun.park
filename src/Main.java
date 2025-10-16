import or.controller.MemberController;
import or.domain.Member;
import or.repository.MemoryMemberRepository;
import or.service.MemberService;
import or.service.MemberServiceImpl;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        MemoryMemberRepository memberRepository = new MemoryMemberRepository();
        MemberService memberService = new MemberServiceImpl(memberRepository);
        MemberController memberController = new MemberController(memberService);

        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\n✨ --- DIVE SOPT 회원 관리 서비스 --- ✨");
            System.out.println("---------------------------------");
            System.out.println("1️⃣. 회원 등록 ➕");
            System.out.println("2️⃣. ID로 회원 조회 🔍");
            System.out.println("3️⃣. 전체 회원 조회 📋");
            System.out.println("4️⃣. 종료 🚪");
            System.out.println("5. 회원 탈퇴");
            System.out.println("---------------------------------");
            System.out.print("메뉴를 선택하세요: ");

            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    System.out.print("등록할 회원 이름을 입력하세요: ");
                    String name = scanner.nextLine();
                    System.out.print("등록할 회원의 생년월일을 입력하세요: ");
                    String birth = scanner.nextLine();
                    System.out.print("등록할 회원의 이메일을 입력하세요: ");
                    String email = scanner.nextLine();
                    System.out.print("등록할 회원의 성별을 입력하세요 (MALE/FEMALE): ");
                    String genderInput = scanner.nextLine();
                    Member.Gender gender;
                    try {
                        gender = Member.Gender.valueOf(genderInput.toUpperCase());
                    } catch (IllegalArgumentException e) {
                        System.out.println("❌ 잘못된 성별입니다. MALE/FEMALE만 입력해주세요");
                        break;
                    }

                    Long createdId = memberController.createMember(name, birth, email, gender);
                    if (createdId != null) {
                        System.out.println("✅ 회원 등록 완료 (ID: " + createdId + ")");
                    } else {
                        System.out.println("❌ 회원 등록 실패");
                    }
                    break;
                case "2":
                    System.out.print("조회할 회원 ID를 입력하세요: ");
                    try {
                        Long id = Long.parseLong(scanner.nextLine());
                        Optional<Member> foundMember = memberController.findMemberById(id);
                        if (foundMember.isPresent()) {
                            System.out.println("✅ 조회된 회원: ID=" + foundMember.get().getId() + ", 이름=" + foundMember.get().getName());
                        } else {
                            System.out.println("⚠️ 해당 ID의 회원을 찾을 수 없습니다.");
                        }
                    } catch (NumberFormatException e) {
                        System.out.println("❌ 유효하지 않은 ID 형식입니다. 숫자를 입력해주세요.");
                    }
                    break;
                case "3":
                    List<Member> allMembers = memberController.getAllMembers();
                    if (allMembers.isEmpty()) {
                        System.out.println("ℹ️ 등록된 회원이 없습니다.");
                    } else {
                        System.out.println("--- 📋 전체 회원 목록 📋 ---");
                        for (Member member : allMembers) {
                            System.out.println("👤 ID=" + member.getId() + ", 이름=" + member.getName());
                        }
                        System.out.println("--------------------------");
                    }
                    break;
                case "4":
                    System.out.println("👋 서비스를 종료합니다. 안녕히 계세요!");
                    scanner.close();
                    return;
                default:
                    System.out.println("🚫 잘못된 메뉴 선택입니다. 다시 시도해주세요.");
                case "5":
                    System.out.print("삭제할 회원 id를 입력하세요: ");
                    try {
                        Long id = Long.parseLong(scanner.nextLine());
                        Optional<Member> foundMember = memberController.findMemberById(id);
                        if (foundMember.isPresent()) {
                            memberController.deleteMember(id);
                            System.out.println("해당 회원이 삭제되었습니다.");
                        } else {
                            System.out.println("⚠️ 해당 ID의 회원을 찾을 수 없습니다.");
                        }
                    } catch (NumberFormatException e) {
                        System.out.println("❌ 유효하지 않은 ID 형식입니다. 숫자를 입력해주세요.");
                    }
                    break;
            }
        }
    }
}