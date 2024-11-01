<?php
require_once('../components/header.php');
?>

<main class="main">
    <div class="main__container">
        <div class="sign__wrapper">
            <h1>Вход</h1>
            <form class="sign__form" action="#">
                <label>
                    Email:<br>
                    <input type="email" name="email" placeholder="Email">
                </label>
                <label>
                    Password:<br>
                    <input type="password" name="password" placeholder="Password">
                </label>
                <button class="add-to-cart" type="submit">Войти</button>
            </form>
        </div>
    </div>
</main>

<?php
require_once('../components/footer.php');
?>