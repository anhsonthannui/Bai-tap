package com.example.tuan3

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImage
import com.example.tuan3.ui.theme.Tuan3Theme // QUAN TRỌNG: Import Theme của bạn


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Tuan3Theme { // Sử dụng Theme của bạn ở đây
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    startDestination ()
                }
            }
        }
    }
}

@Composable
fun startDestination () {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = buoi3bai1.UI_COMPONENTS_LIST) {
        composable(buoi3bai1.UI_COMPONENTS_LIST) {
            UIComponentsListScreen(navController = navController)
        }
        composable(buoi3bai1.TEXT_DETAIL) {
            TextDetailScreen(navController = navController)
        }
        composable(buoi3bai1.IMAGE_DETAIL) {
            ImageDetailScreen(navController = navController)
        }
        composable(buoi3bai1.TEXT_FIELD_DETAIL) {
            TextFieldDetailScreen(navController = navController)
        }
        composable(buoi3bai1.PASSWORD_FIELD_DETAIL) {
            PasswordFieldDetailScreen(navController = navController)
        }
        composable(buoi3bai1.BUTTON_DETAIL) {
            ButtonDetailScreen(navController = navController)
        }
        composable(buoi3bai1.CHECKBOX_DETAIL) {
            CheckboxDetailScreen(navController = navController)
        }
        composable(buoi3bai1.RADIO_BUTTON_DETAIL) {
            RadioButtonDetailScreen(navController = navController)
        }
        composable(buoi3bai1.SWITCH_DETAIL) {
            SwitchDetailScreen(navController = navController)
        }
        composable(buoi3bai1.COLUMN_DETAIL) {
            ColumnDetailScreen(navController = navController)
        }
        composable(buoi3bai1.ROW_DETAIL) {
            RowDetailScreen(navController = navController)
        }
        composable(buoi3bai1.BOX_DETAIL) {
            BoxDetailScreen(navController = navController)
        }
    }
}

// --- Các màn hình ---



data class UIComponentItem(val name: String, val route: String, val description: String? = null)

val uiComponentCategories = mapOf(
    "Display" to listOf(
        UIComponentItem("Text", buoi3bai1.TEXT_DETAIL, "Displays text"),
        UIComponentItem("Image", buoi3bai1.IMAGE_DETAIL, "Displays an image")
    ),
    "Input" to listOf(
        UIComponentItem("TextField", buoi3bai1.TEXT_FIELD_DETAIL, "Input field for text"),
        UIComponentItem("Password Field", buoi3bai1.PASSWORD_FIELD_DETAIL, "Input field for passwords")
    ),
    "Layout" to listOf(
        UIComponentItem("Column", buoi3bai1.COLUMN_DETAIL, "Arranges elements vertically"),
        UIComponentItem("Row", buoi3bai1.ROW_DETAIL, "Arranges elements horizontally"),
        UIComponentItem("Box", buoi3bai1.BOX_DETAIL, "Stacks elements or aligns them")
    )
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UIComponentsListScreen(navController: NavController) {
    Scaffold(
        topBar = {
            TopAppBar(title = { Text("UI Components List") })
        }
    ) { paddingValues ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(horizontal = 16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            uiComponentCategories.forEach { (category, components) ->
                item {
                    Text(
                        text = category,
                        style = MaterialTheme.typography.titleLarge,
                        modifier = Modifier.padding(top = 16.dp, bottom = 8.dp)
                    )
                }
                items(components) { component ->
                    ComponentListItem(component = component) {
                        navController.navigate(component.route)
                    }
                }
            }

        }
    }
}

@Composable
fun ComponentListItem(component: UIComponentItem, onClick: () -> Unit) {
    Card(
        onClick = onClick,
        modifier = Modifier.fillMaxWidth(),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(text = component.name, style = MaterialTheme.typography.titleMedium)
            if (component.description != null) {
                Spacer(modifier = Modifier.height(4.dp))
                Text(text = component.description, style = MaterialTheme.typography.bodySmall)
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailScreenTopAppBar(title: String, navController: NavController) {
    TopAppBar(
        title = { Text(title) },
        navigationIcon = {
            IconButton(onClick = { navController.popBackStack() }) {
                Icon(Icons.Filled.ArrowBack, contentDescription = "Back")
            }
        }
    )
}

@Composable
fun TextDetailScreen(navController: NavController) {
    Scaffold(topBar = { DetailScreenTopAppBar(title = "Text Detail", navController = navController) }) { padding ->
        Column(
            modifier = Modifier.fillMaxSize().padding(padding).padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text("The quick Brown fox jumps over the lazy dog.", style = MaterialTheme.typography.headlineMedium)
            Spacer(modifier = Modifier.height(16.dp))
        }
    }
}

@Composable
fun ImageDetailScreen(navController: NavController) {
    Scaffold(topBar = { DetailScreenTopAppBar(title = "Image Detail", navController = navController) }) { padding ->
        Column(
            modifier = Modifier.fillMaxSize().padding(padding).padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Image(
                painter = painterResource(id = R.drawable.img), // Đảm bảo file này tồn tại
                contentDescription = "In-app Image (Jetpack Compose Logo)",
                modifier = Modifier.size(150.dp)
            )
            Spacer(modifier = Modifier.height(16.dp))
            AsyncImage(
                model = "https://newsmobile.ilea.fr/assets/uploads/2020/07/jetpack-compose-featured.png", // URL ví dụ, bạn có thể thay thế
                contentDescription = "Image from URL",
                modifier = Modifier.fillMaxWidth().height(200.dp),
                contentScale = ContentScale.Fit,
                placeholder = painterResource(id = R.drawable.img_1) // Ảnh placeholder
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TextFieldDetailScreen(navController: NavController) {
    var textValue by remember { mutableStateOf("") }
    Scaffold(topBar = { DetailScreenTopAppBar(title = "TextField Detail", navController = navController) }) { padding ->
        Column(
            modifier = Modifier.fillMaxSize().padding(padding).padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            OutlinedTextField(
                value = textValue,
                onValueChange = { textValue = it },
                label = { Text("Thông tin nhập") },
                placeholder = { Text("Nhập văn bản...")},
                modifier = Modifier.fillMaxWidth()
            )
            Text("Dữ liệu đang nhập: $textValue")
            Text("Tự động cập nhật dữ liệu theo textfield.", style = MaterialTheme.typography.bodySmall)
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PasswordFieldDetailScreen(navController: NavController) {
    var passwordValue by remember { mutableStateOf("") }
    Scaffold(topBar = { DetailScreenTopAppBar(title = "Password Field Detail", navController = navController) }) { padding ->
        Column(
            modifier = Modifier.fillMaxSize().padding(padding).padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            OutlinedTextField(
                value = passwordValue,
                onValueChange = { passwordValue = it },
                label = { Text("Mật khẩu") },
                placeholder = { Text("Nhập mật khẩu...")},
                visualTransformation = PasswordVisualTransformation(),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                modifier = Modifier.fillMaxWidth()
            )
            Text("Mật khẩu đã nhập (ẩn): ${"*".repeat(passwordValue.length)}")
        }
    }
}

@Composable
fun ButtonDetailScreen(navController: NavController) {
    var clickCount by remember { mutableStateOf(0) }
    Scaffold(topBar = { DetailScreenTopAppBar(title = "Button Detail", navController = navController) }) { padding ->
        Column(
            modifier = Modifier.fillMaxSize().padding(padding).padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Button(onClick = { clickCount++ }) { Text("Standard Button") }
            OutlinedButton(onClick = { clickCount++ }) { Text("Outlined Button") }
            TextButton(onClick = { clickCount++ }) { Text("Text Button") }
            Text("Số lần click: $clickCount")
        }
    }
}

@Composable
fun CheckboxDetailScreen(navController: NavController) {
    var isChecked1 by remember { mutableStateOf(false) }
    var isChecked2 by remember { mutableStateOf(true) }
    Scaffold(topBar = { DetailScreenTopAppBar(title = "Checkbox Detail", navController = navController) }) { padding ->
        Column(modifier = Modifier.fillMaxSize().padding(padding).padding(16.dp)) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Checkbox(checked = isChecked1, onCheckedChange = { isChecked1 = it })
                Text("Lựa chọn 1 (Trạng thái: ${if (isChecked1) "Đã chọn" else "Chưa chọn"})")
            }
            Row(verticalAlignment = Alignment.CenterVertically) {
                Checkbox(checked = isChecked2, onCheckedChange = { isChecked2 = it })
                Text("Lựa chọn 2 (Trạng thái: ${if (isChecked2) "Đã chọn" else "Chưa chọn"})")
            }
        }
    }
}

@Composable
fun RadioButtonDetailScreen(navController: NavController) {
    val options = listOf("Lựa chọn A", "Lựa chọn B", "Lựa chọn C")
    var selectedOption by remember { mutableStateOf(options[0]) }
    Scaffold(topBar = { DetailScreenTopAppBar(title = "Radio Button Detail", navController = navController) }) { padding ->
        Column(modifier = Modifier.fillMaxSize().padding(padding).padding(16.dp)) {
            Text("Chọn một:")
            options.forEach { optionText ->
                Row(
                    Modifier.fillMaxWidth().clickable { selectedOption = optionText }.padding(vertical = 4.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    RadioButton(selected = (optionText == selectedOption), onClick = { selectedOption = optionText })
                    Spacer(Modifier.width(8.dp))
                    Text(text = optionText)
                }
            }
            Spacer(Modifier.height(16.dp))
            Text("Đã chọn: $selectedOption")
        }
    }
}

@Composable
fun SwitchDetailScreen(navController: NavController) {
    var isSwitchedOn by remember { mutableStateOf(false) }
    Scaffold(topBar = { DetailScreenTopAppBar(title = "Switch Detail", navController = navController) }) { padding ->
        Column(
            modifier = Modifier.fillMaxSize().padding(padding).padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text("Cài đặt: ")
                Switch(checked = isSwitchedOn, onCheckedChange = { isSwitchedOn = it })
                Text(if (isSwitchedOn) "ON" else "OFF", modifier = Modifier.padding(start = 8.dp))
            }
            Text("Trạng thái Switch: ${if (isSwitchedOn) "Bật" else "Tắt"}")
        }
    }
}

@Composable
fun ColumnDetailScreen(navController: NavController) {
    Scaffold(topBar = { DetailScreenTopAppBar(title = "Column Layout", navController = navController) }) { padding ->
        Column(
            modifier = Modifier.fillMaxSize().padding(padding).padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Text("Các item trong Column:", style = MaterialTheme.typography.titleMedium)
            Box(modifier = Modifier.size(100.dp, 50.dp).background(MaterialTheme.colorScheme.primaryContainer).padding(8.dp), contentAlignment = Alignment.Center) { Text("Item 1") }
            Box(modifier = Modifier.size(100.dp, 50.dp).background(MaterialTheme.colorScheme.secondaryContainer).padding(8.dp), contentAlignment = Alignment.Center) { Text("Item 2") }
            Box(modifier = Modifier.size(100.dp, 50.dp).background(MaterialTheme.colorScheme.tertiaryContainer).padding(8.dp), contentAlignment = Alignment.Center) { Text("Item 3") }
        }
    }
}

@Composable
fun RowDetailScreen(navController: NavController) {
    Scaffold(topBar = { DetailScreenTopAppBar(title = "Row Layout", navController = navController) }) { padding ->
        Column( // Column bao ngoài để thêm tiêu đề
            modifier = Modifier.fillMaxSize().padding(padding).padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text("Các item trong Row:", style = MaterialTheme.typography.titleMedium)
            Spacer(modifier = Modifier.height(16.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                Box(modifier = Modifier.size(80.dp).background(MaterialTheme.colorScheme.primaryContainer).padding(8.dp), contentAlignment = Alignment.Center) { Text("A") }
                Box(modifier = Modifier.size(80.dp).background(MaterialTheme.colorScheme.secondaryContainer).padding(8.dp), contentAlignment = Alignment.Center) { Text("B") }
                Box(modifier = Modifier.size(80.dp).background(MaterialTheme.colorScheme.tertiaryContainer).padding(8.dp), contentAlignment = Alignment.Center) { Text("C") }
            }
            // Mô phỏng các ô vuông xanh như trong hình bài tập
            Spacer(modifier = Modifier.height(24.dp))
            Text("Ví dụ layout Row (hình bài tập):", style = MaterialTheme.typography.titleMedium)
            Spacer(modifier = Modifier.height(8.dp))
            Column(verticalArrangement = Arrangement.spacedBy(4.dp)){
                Row(horizontalArrangement = Arrangement.spacedBy(4.dp)) { (1..3).forEach { DemoBlueBox() } }
                Row(horizontalArrangement = Arrangement.spacedBy(4.dp)) { (1..3).forEach { DemoBlueBox() } }
                Row(horizontalArrangement = Arrangement.spacedBy(4.dp)) { (1..3).forEach { DemoBlueBox() } }
            }
        }
    }
}

@Composable
fun DemoBlueBox() {
    Box(modifier = Modifier.size(50.dp).background(Color(0xFFBBDEFB))) // Màu xanh nhạt
}


@Composable
fun BoxDetailScreen(navController: NavController) {
    Scaffold(topBar = { DetailScreenTopAppBar(title = "Box Layout", navController = navController) }) { padding ->
        Column(
            modifier = Modifier.fillMaxSize().padding(padding).padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ){
            Text("Box cho phép chồng các thành phần:", style = MaterialTheme.typography.titleMedium)
            Spacer(modifier = Modifier.height(16.dp))
            Box(
                modifier = Modifier.size(200.dp).background(Color.LightGray),
                contentAlignment = Alignment.Center
            ) {
                Box(modifier = Modifier.size(150.dp).background(MaterialTheme.colorScheme.primary.copy(alpha = 0.7f)))
                Box(modifier = Modifier.size(100.dp).background(MaterialTheme.colorScheme.secondary.copy(alpha = 0.7f)))
                Text("Chồng lớp", Modifier.align(Alignment.BottomCenter).padding(8.dp))
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    Tuan3Theme {
        UIComponentsListScreen(rememberNavController())
    }
}