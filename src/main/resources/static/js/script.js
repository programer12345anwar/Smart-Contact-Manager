console.log("this is script file");
//alert("this is for testing");

document.querySelector('.navbar-toggler').addEventListener('click', function () {
    const navbarCollapse = document.querySelector('.navbar-collapse');
    if (navbarCollapse.classList.contains('show')) {
        navbarCollapse.style.maxHeight = '0'; // Collapse effect
    } else {
        navbarCollapse.style.maxHeight = '300px'; // Expand effect
    }
});

const toggleSidebar = () => {
     if($('.sidebar').is(":visible")){
        $('.sidebar').css("display","none");
        $('.content').css("margin-left","0%");
    }else{
        $('.sidebar').css("display","block");
        $('.content').css("margin-left","18%");
    }
};
