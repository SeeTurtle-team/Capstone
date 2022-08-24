<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, minimum-scale=1, maximum-scale=1">
    <title>Pront bar</title>
    <link rel="stylesheet" href="https://unpkg.com/swiper/swiper-bundle.css" />
    <link rel="stylesheet" href="https://unpkg.com/swiper/swiper-bundle.min.css" />
    <link rel="stylesheet" href="/resources/css/index.css" />
    <link rel="preconnect" href="https://fonts.googleapis.com" />
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Electrolize&display=swap" rel="stylesheet">
    <link rel="stylesheet" href="jquery.fullPage.css">
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Koulen&display=swap" rel="stylesheet">
    <script src="https://unpkg.com/swiper/swiper-bundle.js"></script>
    <script src="https://unpkg.com/swiper/swiper-bundle.min.js"></script>
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"
        integrity="sha256-/xUj+3OJU5yExlq6GSYGSHk7tPXikynS7ogEvDej/m4=" crossorigin="anonymous"></script>
    <script src="/resources/js/jquery.fullPage.js"></script>
    <script src="/resources/js/main.js"></script>
    <script src="https://kit.fontawesome.com/af4e1eff79.js" crossorigin="anonymous"></script>


</head>
<script>
function logOut(){
	console.log("logOut!!");
	
	alert("로그아웃 되었습니다!");
	location.href = "/"+"?option=logOut";
}
</script>
<body>
    <div id="fullpage">




        <section class="section">

            <div class="wrap">
                <header id="index_header" class="header">
                    <nav class="prontbar" id="index_prontbar">
                        <div class="bar_logo">
                            <i class="fa-solid fa-car-crash"></i>
                            <a href="/">HansungProject</a>
                        </div>

                        <ul class="bar_menu">
                            <li><a href="/carList">CCTV</a></li>
                            <li><a href="/CarModel">Vehicle </a></li>
                            <li><a href="/free">Spaces</a></li>
                            <li><a href="/QnA">Questions</a></li>
                            <li><a href="/developer">Developer</a></li>

                        </ul>


                        <div class="Login_menu" onclick="logOut()"> 
                        	<p>${login}</p>&nbsp;&nbsp;
                        	<p onclick = "logOut">logOut</p>
                        </div>


                        <ul class="bar_icons">
                            <li><i class="fa-brands fa-twitter"></i></li>
                            <li><i class="fa-brands fa-facebook"></i></li>
                        </ul>

                        <a href="#" class="bar_toogle">
                            <i class="fa-solid fa-bars"></i>
                        </a>
                    </nav>
                </header>

                <pre class="bugi">
                            안녕하세요!

                   저희들을 소개해볼게요.

       세상을 바꾸는 개발자가 되고 싶어요.
        </pre>
                <img class="bugiimg" src="https://ifh.cc/g/xdjdg7.png">
                <img id="down" src="https://ifh.cc/g/XDRRCL.png"></img>


            </div>
        </section>
        
        <section class="section">
            <div class="section section_fullpage_dev">
                <div class="developerBigBox">
                    <div id="developer_name">L A</div>
                    <div class="whitebox">

                        <img id="dev_face" src="https://ifh.cc/g/xdjdg7.png">

                        <div id="white_name"><span>L A</span><br>G&nbsp;U&nbsp;N&nbsp;H&nbsp;O</div>
                        <div id="middle_name">
                            <hr>
                            <i class="fa-solid fa-phone">&emsp;&emsp;010-2703-8311</i>
                            <hr>
                            <i class="fa-solid fa-envelope">&emsp;&emsp;gunho30811@naver.com</i>
                            <hr>
                            <i class="fa-solid fa-location-dot">&emsp;&emsp;안남로 272 금호아파트 204동</i>
                        </div>
                    </div>
                    <div class="detail_box">
                        <div class="education">Education
                            <ul>
                                <li>그래픽정보처리 A+</li>
                                <li>컴퓨터네트워크 A</li>
                                <li>데이터베이스 A+</li>
                                <li>데이터베이스 응용 A+</li>
                            </ul>
                        </div>
                        <div class="education"> Activities
                            <ul>
                                <li>Mathematics Teacher</li>
                                <li>Blizzard Competition MC</li>
                                <li>Hansung_Success Program</li>
                            </ul>
                        </div>

                    </div>

                    <div class="detail_box">
                        <div class="education"> Skills
                            <ul>
                                <li>JAVA</li>
                                <li>HTML,CSS,JS</li>
                                <li>React</li>
                                <li>Photoshop</li>
                            </ul>
                        </div>
                        <div class="education"> Work
                            <ul>
                                <li>FrontEnd</li>
                                <li>Photoshop</li>
                                <li>Communication</li>
                            </ul>
                        </div>



                    </div>

                </div>

        </section>



        <section class="section">
            <div class="section section_fullpage_dev">
                <div class="developerBigBox">
                    <div id="developer_name">CHO</div>
                    <div class="whitebox">

                        <img id="dev_face" src="https://ifh.cc/g/9SzrSo.png">

                        <div id="white_name"><span>CHO</span><br>B&nbsp;Y&nbsp;U&nbsp;N&nbsp;G&nbsp;&nbsp;K&nbsp;Y&nbsp;U</div>
                        <div id="middle_name">
                            <hr>
                            <i class="fa-solid fa-phone">&emsp;&emsp;010-9230-9536</i>
                            <hr>
                            <i class="fa-solid fa-envelope">&emsp;&emsp;qudqud97@naver.com</i>
                            <hr>
                            <i class="fa-solid fa-location-dot">&emsp;&emsp;은평구 응암동</i>
                        </div>
                    </div>
                    <div class="detail_box">
                        <div class="education">Education
                            <ul>
                                <li>통신공학 A+</li>
                                <li>데이터베이스응용 A+</li>
                                <li>전자기학 A+</li>
                            </ul>
                        </div>
                        <div class="education"> Activities
                            <ul>
                                <li>IPP</li>
                                <li>Capstone Project</li>
                                <li>Hansung_Success Program</li>
                            </ul>
                        </div>

                    </div>

                    <div class="detail_box">
                        <div class="education"> Skills
                            <ul>
                                <li>JAVA </li>
                                <li>HTML</li>
                                <li>PHP</li>
                                <li>DB</li>
                            </ul>
                        </div>
                        <div class="education"> Work
                            <ul>
                                <li>JAVA</li>
                                <li>DB</li>
                                <li>team Leader</li>
                            </ul>
                        </div>



                    </div>

                </div>

        </section>

        <section class="section">
            <div class="section section_fullpage_dev">
                <div class="developerBigBox">
                    <div id="developer_name">CHO</div>
                    <div class="whitebox">

                        <img id="dev_face" src="https://ifh.cc/g/mOkT2Z.png">

                        <div id="white_name"><span>CHO</span><br>Y&nbsp;O&nbsp;O&nbsp;N&nbsp;&nbsp;T&nbsp;A&nbsp;E</div>
                        <div id="middle_name">
                            <hr>
                            <i class="fa-solid fa-phone">&emsp;&emsp;010-4538-9853</i>
                            <hr>
                            <i class="fa-solid fa-envelope">&emsp;&emsp;roy1228@naver.com</i>
                            <hr>
                            <i class="fa-solid fa-location-dot">&emsp;&emsp;한성대학교 상상관 404</i>
                        </div>
                    </div>
                    <div class="detail_box">
                        <div class="education">Education
                            <ul>
                                <li>마이크로프로세스 A+</li>
                                <li>데이터베이스응용 A+</li>
                                <li>데이터베이스 A+</li>
                            </ul>
                        </div>
                        <div class="education"> Activities
                            <ul>
                                <li>Capstone Project</li>
                                <li>Hansung_Success Program</li>
                            </ul>
                        </div>

                    </div>

                    <div class="detail_box">
                        <div class="education"> Skills
                            <ul>
                                <li>JAVA</li>
                                <li>HTML</li>
                                <li>PHP</li>
                                <li>DB</li>
                            </ul>
                        </div>
                        <div class="education"> Work
                            <ul>
                                <li>JAVA</li>
                                <li>DB</li>
                                <li>Sub Leader</li>
                            </ul>
                        </div>



                    </div>

                </div>

        </section>

        <section class="section">
            <div class="section section_fullpage_dev">
                <div class="developerBigBox">
                    <div id="developer_name">K I M</div>
                    <div class="whitebox">

                        <img id="dev_face" src="https://ifh.cc/g/jZwpk0.png">

                        <div id="white_name"><span>K I M</span><br>M&nbsp;I&nbsp;N&nbsp;&nbsp;S&nbsp;U&nbsp;</div>
                        <div id="middle_name">
                            <hr>
                            <i class="fa-solid fa-phone">&emsp;&emsp;010-5751-8250</i>
                            <hr>
                            <i class="fa-solid fa-envelope">&emsp;&emsp;soo11jji@naver.com</i>
                            <hr>
                            <i class="fa-solid fa-location-dot">&emsp;&emsp;상계역 염광아파트</i>
                        </div>
                    </div>
                    <div class="detail_box">
                        <div class="education">Education
                            <ul>
                                <li>데이터베이스 A+</li>
                                <li>그래픽정보처리 A+</li>
                                <li>통신공학 A+</li>
                                
                            </ul>
                        </div>
                        <div class="education"> Activities
                            <ul>
                                <li>Overseas Volunteer</li>
                                <li>Capstone project</li>
                                <li>Hansung_Success Program</li>
                            </ul>
                        </div>

                    </div>

                    <div class="detail_box">
                        <div class="education"> Skills
                            <ul>
                                <li>Python</li>
                                <li>Deep Learning</li>
                                
                            </ul>
                        </div>
                        <div class="education"> Work
                            <ul>
                                <li>Yolov4</li>
                                <li>OpenCV</li>
                                <li>Darknet</li>
                                <li>Colab</li>
                            </ul>
                        </div>



                    </div>

                </div>

        </section>

        <section class="section">
            <div class="section section_fullpage_dev">
                <div class="developerBigBox">
                    <div id="developer_name">K I M</div>
                    <div class="whitebox">

                        <img id="dev_face" src="https://ifh.cc/g/yN2Z5j.png">

                        <div id="white_name"><span>K I M</span><br>Y&nbsp;O&nbsp;N&nbsp;G&nbsp;&nbsp;W&nbsp;O&nbsp;O</div>
                        <div id="middle_name">
                            <hr>
                            <i class="fa-solid fa-phone">&emsp;&emsp;010-3187-2295</i>
                            <hr>
                            <i class="fa-solid fa-envelope">&emsp;&emsp;youngw77@naver.com</i>
                            <hr>
                            <i class="fa-solid fa-location-dot">&emsp;&emsp;성북경찰서 뒷편</i>
                        </div>
                    </div>
                    <div class="detail_box">
                        <div class="education">Education
                            <ul>
                                <li>Hansung_Univ</li>
                                <li>Eduwill</li>
                                <li>제어공학 A+</li>

                            </ul>
                        </div>
                        <div class="education"> Activities
                            <ul>
                                <li>IPP</li>
                                <li>Capstone project</li>
                                <li>Hansung_Success Program</li>
                            </ul>
                        </div>

                    </div>

                    <div class="detail_box">
                        <div class="education"> Skills
                            <ul>
                                <li>JAVA</li>
                                <li>Python</li>
                                <li>PHP</li>
                                <li>Yolov4</li>
                            </ul>
                        </div>
                        <div class="education"> Work
                            <ul>
                                <li>Yolov4</li>
                                <li>OpenCV</li>
                                <li>Darknet</li>
                                <li>Colab</li>
                            </ul>
                        </div>



                    </div>

                </div>

        </section>
    </div>
    <div class="top">
        <!--5.10-->
        <a href="" onClick="javascript:window.scrollTo(0,0)">
            <p>TOP</p>
            <img src="http://www.netchus.com/resources/img/layout/top.png">
        </a>
    </div>

    <script>
        $(document).ready(function () {

            $("a").on('click', function (event) {

                if (this.hash !== "") {

                    event.preventDefault();

                    var hash = this.hash;

                    $('html, body').animate({

                        scrollTop: $(hash).offset().top

                    }, 800, function () {

                        window.location.hash = hash;

                    });

                }

            });

        });



    </script>​

    <script>
        const swiper = new Swiper('.swiper-container',
            {
                direction: 'horizontal',
                slidesPerView: 3,
                spaceBetween: 30,
                effect: 'fade',
                centeredSlides: true,
                autoplay: {
                    delay: 4000,
                    disableOnlnteraction: false,
                },
            }

        );

        $(function () {
            $("banner-title_two")

        });



    </script>-->




    <!----<script>

    $(window).scroll(function() {
            var scroll = $(window).scrollTop();
            if (scroll >= 650) {
                $(".main").addClass("active");
            }
        });
             
    
            
    
    
</script> ---->




</body>





</html>