package com.tianao.module.net.entity.httpResult;

public class EncodeResult {
    /**
     * iv : HpY2PdkrLTWi6RufWrNf3g==
     * value : 6QTDbsSatPuMdBw6UfhhIgB/qCmODYC0ti9NiF9I3xdLZ2HWeuZRW6kldwk/1F02tjsIL/6WnomOT0ocb9pokxoDcyAoXNq98DU1NTJrgOeCymzBoEKJi80M7lOuY6PPS/6hibiPsR15PGmiAqmFHfw4Lm3GbdwedKpVqoT4yES4/af+tRQR4UwG2zmCEkPf/LjiHkyO0vdmE+FzcHO9a/EiNV5rdg6xbVGe9sfKRHIgwXJMls48/oa4w8ZrZ9R0k7w/3j1E7FUGH3sjMEY92azLQA4IFUGe60qAmi3nHhJLjMRgGNuWDxzEqbTPPUNpX5LnNFo7MO3Smo9b4oJQGUmYcRYmwhn/la0KJgq5Hazq8nUtA7Q8t/32WzV3UPkzWzbHMtBZA8KOjk4u8R54wsNkCziJspw1qnajbAsNn1FF1XEQaLalN3lm3Q3efl7Zb7z7UQQIm04JU5Td2agE6aTInHM4Ph2SXSUlHiBtRaKJ0Av68qoaz5bKXmrySd/QNFImxJirYYWKegjSliK0g+ks2YmtiWIN57jo2iPbLHJRqjlmX+4KKl3Y6oSUfGn4iu7zBsUskjwbjLBFvxnpBUV5HfHjohrCs/k2ye16FMDs+R0qPSY1ZAPnFXYRSMhA5MD+iM0yjNrbW7/FwbtMRgoF661FTfeikyRqIGrnonaN1KJnn051/a7VPyCh6hNnxQdUGC8RQ3KWytZgLxmX4YROl5hhNUs0NvoRoTiJIC4=
     * mac : 7069ccd7f3c5fc34a33a1ee3aee10ff3653b13590a7eb71c643566ec40fcc9cd
     * key : C5HrVP6qNUmLzeb7FBZxSi8wd4lvKWgRa39AfEh0nMj=
     */
    public String iv;
    public String value;
    public String mac;
    public String key;

    @Override
    public String toString() {
        return "AppResult{" +
                "iv='" + iv + '\'' +
                ", value='" + value + '\'' +
                ", mac='" + mac + '\'' +
                ", key='" + key + '\'' +
                '}';
    }
}
