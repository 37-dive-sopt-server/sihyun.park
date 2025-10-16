package or.domain;

public class Member {

    private Long id;
    private String name;
    private String birth;
    private String email;
    private Gender gender;

    public Member(Long id, String name, String birth, String email, Gender gender) {
        this.id = id;
        this.name = name;
        this.birth = birth;
        this.email = email;
        this.gender = gender;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getBirth(){
        return birth;
    }

    public String getEmail(){
        return email;
    }

    public Gender getGender(){
        return gender;
    }
}