<?php
require_once('../components/header.php');
?>

<main class="main">
    <div class="main__container">
        <div class="sign__wrapper">
            <h1>Регистрация</h1>
            <form class="sign__form" action="#">
                <label>
                    Name:<br>
                    <input type="name" name="name" placeholder="Name">
                </label>
                <label>
                    Email:<br>
                    <input type="email" name="email" placeholder="Email">
                </label>
                <label>
                    Password:<br>
                    <input type="password" name="password" placeholder="Password">
                </label>
                <label>
                    Confirm password:<br>
                    <input type="password" name="confirm" placeholder="Confirm">
                </label>
                <button class="add-to-cart" type="submit">Войти</button>
            </form>
        </div>
    </div>
</main>

<?php
require_once('../components/footer.php');
?>