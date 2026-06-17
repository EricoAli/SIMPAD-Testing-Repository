Feature: Authentication Flow SIMPAD
  Sebagai pengguna sistem
  Saya ingin bisa login dan logout dengan aman menggunakan Google SSO
  Agar saya dan anggota tim lain bisa menjalankan fitur utama aplikasi

  Scenario: Login sukses dengan akun valid
    Given user berada di "Landing Page Pre-Login"
    When user navigasi ke "Login Page"
    And user melakukan login SSO dengan akun Google kampus yang valid
    Then user berhasil masuk dan diarahkan ke "Landing Page Post-Login"

  Scenario: Akses halaman internal tanpa login
    Given user belum melakukan login ke dalam sistem
    When user mencoba mengakses URL "Buat Project" secara langsung
    Then sistem langsung mengarahkan user kembali ke "Login Page"

  Scenario: Logout sukses dari sistem
    Given user sudah berhasil login dan berada di "Landing Page Post-Login"
    When user menekan tombol Logout
    Then sesi berakhir dan user diarahkan kembali ke "Landing Page Pre-Login"