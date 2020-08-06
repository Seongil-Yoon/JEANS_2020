import gulp from "gulp";
import del from "del";
import sass from "gulp-sass";
import minify from "gulp-csso";
import autoprefixer from "gulp-autoprefixer";

sass.compiler = require("node-sass");

const routes = {
  css: {
    watch: "static/scss/*",
    src: [
      "static/scss/changeUser.scss",
      "static/scss/intro.scss",
      "static/scss/jeans_header_.scss",
      "static/scss/jeans_info_body.scss",
      "static/scss/jeans_look_body.scss",
      "static/scss/jeans_write_body.scss",
      "static/scss/login.scss",
      "static/scss/look_list.scss",
      "static/scss/look_list_Look.scss",
      "static/scss/mypageUser_bottom.scss",
      "static/scss/mypageUser_top.scss",
      "static/scss/joinUser.scss",
      "static/scss/slideShow.scss",
      "static/scss/fullpage.min.scss"
    ],
    dest: "static/css"
  }
};




const styles = () =>
  gulp
    .src(routes.css.src)
    .pipe(sass().on("error", sass.logError))
    .pipe(
      autoprefixer({
        flexbox: true,
        grid: "autoplace"
      })
    )
    .pipe(minify())
    .pipe(gulp.dest(routes.css.dest));

const watch = () => {
  gulp.watch(routes.css.watch, styles);
};

const clean = () => del(["dist/styles.css"]);

const prepare = gulp.series([clean]);

const assets = gulp.series([styles]);

const live = gulp.parallel([watch]);

export const dev = gulp.series([prepare, assets, live]);
