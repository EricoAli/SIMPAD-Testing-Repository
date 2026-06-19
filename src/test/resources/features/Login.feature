Feature: Login SIMPAD SSO UGM

  Scenario: Login sukses menggunakan email SSO UGM
    Given Saya berada di halaman utama SIMPAD
    When Saya memilih login dengan Email UGM
    And Saya melakukan otentikasi Google dengan email "delvianokhayruattahira@mail.ugm.ac.id"
    And Saya memasukkan kredensial SSO UGM dengan email "delvianokhayruattahira" dan password "eND3AVOR123"
    And Saya menyetujui layar persetujuan Google
    Then Saya harus berhasil masuk dan melihat ikon profil di dashboard